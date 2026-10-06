var fotosIntegrantes = [
    "imagens/Amanda.png",
    "imagens/Bianca.png",
    "imagens/Iury.png",
    "imagens/Joaquim.png",
    "imagens/Kaike.png",
    "imagens/Lauro.png",
    "imagens/Victor.png"
];
var indiceFotoAtual = -1;

document.getElementById("foto").addEventListener("click", function () {
    if (indiceFotoAtual === -1) {
        return;
    }

    indiceFotoAtual = (indiceFotoAtual + 1) % fotosIntegrantes.length;
    var alunoAtual = alunos[indiceFotoAtual];

    document.getElementById("foto").src = fotosIntegrantes[indiceFotoAtual];
    document.getElementById("nomeIntegrante").textContent = alunoAtual.nome;
    document.getElementById("nomeVaga").textContent = alunoAtual.vaga;
    document.getElementById("descricaoVaga").textContent = alunoAtual.descricao;
});

function buscarFoto() {
    var foto = document.getElementById("foto");
    var mensagem = document.getElementById("mensagem");

    // Tratamento: remove espaços e transforma tudo em MAIÚSCULA
    var matricula = document.getElementById("matricula").value.trim().toUpperCase();

    var caminho = ""; // guarda o caminho da foto encontrada

    // Campo vazio: avisa e para aqui
    if (matricula === "") {
        foto.style.display = "none";
        mensagem.textContent = "Por favor, informe uma matrícula.";
        return;
    }

    // As matrículas dos case precisam estar SEMPRE em MAIÚSCULA
    switch (matricula) {
        case "12221BCC016":
            caminho = "imagens/Amanda.png";
            break;

        case "12111BCC053":
            caminho = "imagens/Bianca.png";
            break;

        case "12411BCC040":
            caminho = "imagens/Iury.png";
            break;

        case "12421BSI382":
            caminho = "imagens/Joaquim.png";
            break;

        case "12421BCC051":
            caminho = "imagens/Kaike.png";
            break;

        case "12411BCC028":
            caminho = "imagens/Lauro.png";
            break;

        case "12211BCC050":
            caminho = "imagens/Victor.png";
            break;

        default:
            caminho = "";
    }

    if (caminho !== "") {
        indiceFotoAtual = fotosIntegrantes.indexOf(caminho);

        // Se o arquivo da imagem não for encontrado, mostra qual caminho falhou
        foto.onerror = function () {
            mensagem.textContent = "Não foi possível carregar a foto: " + caminho +
                " (confira a pasta e o nome do arquivo).";
        };
        foto.src = caminho;
        foto.style.display = "block";
        mensagem.textContent = "Integrante encontrado!";
    } else {
        foto.style.display = "none";
        mensagem.textContent = "Matrícula não encontrada. Verifique o número digitado.";
    }
}