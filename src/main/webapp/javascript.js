var req;
var isIE;
var placa;
var anoModelo;
var cor;
var anoFabricacao;

var lista;

function init() {
	placa = document.getElementById("inputPlaca");
	anoModelo = document.getElementById("inputAnoModelo");
	cor = document.getElementById("inputCor");
	anoFabricacao = document.getElementById("inputAnoFabricacao");

//	lista = document.getElementById("tblVeiculo");

}

function salvar() {

	var url = "veiculo?action=salvar&placa=" + escape(placa.value)
			+ "&anoModelo=" + escape(anoModelo.value) + "&cor="
			+ escape(cor.value) + "&anoFabricacao="
			+ escape(anoFabricacao.value);
	req = initRequest();
	req.open("GET", url, true);
}

function initRequest() {
	if (window.XMLHttpRequest) {
		if (navigator.userAgent.indexOf('MSIE') != -1) {
			isIE = true;
		}
		return new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		return new ActiveXObject("Microsoft.XMLHTTP");
	}
}

