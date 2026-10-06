var formCadastro = document.getElementById("formCadastro");
var primeiroCampo = document.getElementById("user");
var seletorIntegrante = document.getElementById("integrante");
var relatorio = document.getElementById("relatorio");

primeiroCampo.addEventListener("focus", function () {
    primeiroCampo.style.backgroundColor = "lightyellow";
});

document.getElementById("botaoCadastro").addEventListener("click", function () {
    if (!formCadastro.reportValidity()) {
        return;
    }

    relatorio.textContent = "";

    var titulo = document.createElement("h2");
    titulo.textContent = "Relatório do Cadastro";
    relatorio.appendChild(titulo);

    var campos = formCadastro.querySelectorAll("input[type='text'], select");
    var lista = document.createElement("ul");

    campos.forEach(function (campo) {
        var item = document.createElement("li");
        var label = document.querySelector("label[for='" + campo.id + "']");
        var valor = campo.tagName === "SELECT"
            ? campo.options[campo.selectedIndex].text
            : campo.value;

        item.textContent = label.textContent + " " + valor;
        lista.appendChild(item);
    });

    relatorio.appendChild(lista);

    var foto = document.createElement("img");
    foto.src = seletorIntegrante.value;
    foto.alt = "Foto de " + seletorIntegrante.options[seletorIntegrante.selectedIndex].text;
    foto.width = 200;
    relatorio.appendChild(foto);
});