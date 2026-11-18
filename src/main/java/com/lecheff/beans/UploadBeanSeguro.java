package com.lecheff.beans;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

public class UploadBeanSeguro {

    private static final long MB = 1024L * 1024L;

    private String diretorio;
    private String contextoUsuario; // "cliente" ou "cheff"
    private int size;               // tamanho máximo em MB
    private String extensoesPermitidas;
    private String erro = null;
    private List<String> filenames = new ArrayList<>();
    private List<String> savedFilenames = new ArrayList<>();

    public String getDiretorio() { return diretorio; }
    public void setDiretorio(String diretorio) { this.diretorio = diretorio; }

    public String getContextoUsuario() { return contextoUsuario; }
    public void setContextoUsuario(String contextoUsuario) { this.contextoUsuario = contextoUsuario; }

    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }

    public String getExtensoesPermitidas() { return extensoesPermitidas; }
    public void setExtensoesPermitidas(String extensoesPermitidas) { this.extensoesPermitidas = extensoesPermitidas; }

    public String getErro() { return erro; }
    public void setErro(String erro) { this.erro = erro; }

    public List<String> getFilenames() { return filenames; }
    public List<String> getSavedFilenames() { return savedFilenames; }

    public boolean doFilePost(HttpServletRequest request, ServletContext context) {
        filenames.clear();
        savedFilenames.clear();
        erro = null;

        if (request.getContentType() == null) return false;
        if (!request.getContentType().toLowerCase().startsWith("multipart/form-data")) {
            setErro("Seu formulário não envia arquivos.");
            return false;
        }

        try {
            String pastaContexto = getDiretorio() + File.separator + getContextoUsuario();
            String path = context.getRealPath(pastaContexto);
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            Collection<Part> parts = request.getParts();
            int arquivosValidos = 0;

            for (Part part : parts) {
                if (part.getSubmittedFileName() != null && !part.getSubmittedFileName().isEmpty()) {
                    String nomeOriginal = Paths.get(part.getSubmittedFileName()).getFileName().toString();

                    if (part.getSize() > (getSize() * MB)) {
                        setErro("Tamanho excedido no arquivo: " + nomeOriginal + " (Máx: " + getSize() + "MB)");
                        return false;
                    }

                    if (!isPermission(nomeOriginal)) {
                        setErro("Extensão não permitida no arquivo: " + nomeOriginal + ". Permitidos: " + extensoesPermitidas);
                        return false;
                    }

                    String nomeSalvo = UUID.randomUUID().toString().substring(0, 8) + "_" + nomeOriginal;
                    File destino = new File(dir, nomeSalvo);

                    try (InputStream input = part.getInputStream()) {
                        Files.copy(input, destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }

                    filenames.add(nomeOriginal);
                    savedFilenames.add(nomeSalvo);
                    arquivosValidos++;
                }
            }

            if (arquivosValidos < 2) {
                setErro("É obrigatório selecionar e enviar 2 arquivos.");
                return false;
            }

            return true;

        } catch (Exception e) {
            setErro("Uma exceção ocorreu: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean isPermission(String fileName) {
        String lowerCaseName = fileName.toLowerCase();
        StringTokenizer st = new StringTokenizer(extensoesPermitidas, ",");
        while (st.hasMoreTokens()) {
            String ext = st.nextToken().trim().toLowerCase();
            if (lowerCaseName.endsWith("." + ext)) {
                return true;
            }
        }
        return false;
    }

    public File[] listarArquivosDoContexto(ServletContext context) {
        String pastaContexto = getDiretorio() + File.separator + getContextoUsuario();
        String path = context.getRealPath(pastaContexto);
        if (path == null) return new File[0];
        File dir = new File(path);
        if (!dir.exists() || !dir.isDirectory()) return new File[0];
        File[] files = dir.listFiles();
        return (files != null) ? files : new File[0];
    }
}