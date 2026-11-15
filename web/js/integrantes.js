const integrantes = [
    {
        matricula: "12221BCC016",
        nome: "Amanda",
        vaga: "Ciência de dados",
        descricao: "Aplicado à Astronomia | Desenvolvendo modelos de IA e Visão Computacional para analisar o universo através de imagens.",
        foto: "imagens/Amanda.png"
    },
    {
        matricula: "12111BCC053",
        nome: "Bianca",
        vaga: "Analista de Dados",
        descricao: "Coleta, organiza e analisa dados para gerar insights que apoiam a tomada de decisões.",
        foto: "imagens/Bianca.png"
    },
    {
        matricula: "12411BCC040",
        nome: "Iury",
        vaga: "Desenvolvedor Backend",
        descricao: "Constrói e mantém a lógica interna, as APIs e os bancos de dados que fazem um sistema funcionar de forma rápida, segura e escalável por trás da interface.",
        foto: "imagens/Iury.png"
    },
    {
        matricula: "12421BSI382",
        nome: "Joaquim",
        vaga: "Desenvolvedor FullStack",
        descricao: "Transforma o design e a regra de negócio da aplicação em código de forma intuitiva, escalável e segura",
        foto: "imagens/Joaquim.png"
    },
    {
        matricula: "12421BCC051",
        nome: "Kaike",
        vaga: "Desenvolvedor Fullstack",
        descricao: "Transforma o design e a regra de negócio da aplicação em código de forma intuitiva, escalável e segura.",
        foto: "imagens/Kaike.png"
    },
    {
        matricula: "12411BCC028",
        nome: "Lauro",
        vaga: "Front-End",
        descricao: "Transformar o design visual do projeto em uma interface digital funcional utilizando HTML, CSS e JavaScript.",
        foto: "imagens/Lauro.png"
    },
    {
        matricula: "12211BCC050",
        nome: "Victor",
        vaga: "Desenvolvedor Fullstack",
        descricao: "Cria aplicações completas, trabalhando tanto na parte visual quanto na lógica de servidores e dados.",
        foto: "imagens/Victor.png"
    }
];

// Monta um card para cada integrante e coloca na section
const secao = document.getElementById("lecheff__integrantes");

for (const integrante of integrantes) {
    secao.innerHTML +=
        '<div class="lecheff__integrante">' +
        '  <img src="' + integrante.foto + '" alt="Foto de ' + integrante.nome + '">' +
        '  <h3>' + integrante.nome + '</h3>' +
        '  <p>' + integrante.vaga + '</p>' +
        '</div>';
}