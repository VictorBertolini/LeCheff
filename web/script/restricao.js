// As matrículas aqui precisam estar SEMPRE em MAIÚSCULA
var alunos = [
    {
        matricula: "12221BCC016",
        nome: "Amanda",
        vaga: "Ciência de dados",
        descricao: "Aplicado à Astronomia | Desenvolvendo modelos de IA e Visão Computacional para analisar o universo através de imagens."
    },
    {
        matricula: "12111BCC053",
        nome: "Bianca",
        vaga: "Analista de Dados",
        descricao: "Coleta, organiza e analisa dados para gerar insights que apoiam a tomada de decisões."
    },
    {
        matricula: "12411BCC040",
        nome: "Iury",
        vaga: "Desenvolvedor Backend",
        descricao: "Constrói e mantém a lógica interna, as APIs e os bancos de dados que fazem um sistema funcionar de forma rápida, segura e escalável por trás da interface."
    },
    {
        matricula: "12421BSI382",
        nome: "Joaquim",
        vaga: "Desenvolvedor FullStack",
        descricao: "Transforma o design e a regra de negócio da aplicação em código de forma intuitiva, escalável e segura"
    },
    {
        matricula: "12421BCC051",
        nome: "Kaike",
        vaga: "Desenvolvedor Fullstack",
        descricao: "Transforma o design e a regra de negócio da aplicação em código de forma intuitiva, escalável e segura."
    },
    {
        matricula: "12411BCC028",
        nome: "Lauro",
        vaga: "Front-End",
        descricao: "Transformar o design visual do projeto em uma interface digital funcional utilizando HTML, CSS e JavaScript."
    },
    {
        matricula: "12211BCC050",
        nome: "Victor",
        vaga: "Desenvolvedor Fullstack",
        descricao: "Cria aplicações completas, trabalhando tanto na parte visual quanto na lógica de servidores e dados."
    }
];

function buscarVaga() {
    // Tratamento: remove espaços e transforma tudo em MAIÚSCULA
    var matricula = document.getElementById("matricula").value.trim().toUpperCase();

    // Elementos HTML
    var mensagem = document.getElementById("mensagem");
    var resultado = document.getElementById("resultado");

    // Controle da pesquisa
    var encontrado = null;

    // Oculta o resultado anterior
    resultado.style.display = "none";

    // Verifica se o campo está vazio
    if (matricula === "") {
        mensagem.textContent = "Por favor, informe uma matrícula.";
        return;
    }

    // for percorre o array comparando cada matrícula
    for (var i = 0; i < alunos.length; i++) {
        if (alunos[i].matricula === matricula) {
            encontrado = alunos[i];
            break;
        }
    }

    if (encontrado !== null) {
        document.getElementById("nomeIntegrante").textContent = encontrado.nome;
        document.getElementById("nomeVaga").textContent = encontrado.vaga;
        document.getElementById("descricaoVaga").textContent = encontrado.descricao;
        mensagem.textContent = "";
        resultado.style.display = "block";
    } else {
        mensagem.textContent = "Matrícula não encontrada. Verifique o número digitado.";
    }
}