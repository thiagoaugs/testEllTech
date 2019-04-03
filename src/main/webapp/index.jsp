<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro de veiculo</title>

<script type="text/javascript" src="javascript.js"></script>
</head>
<body onload="init()">
		<h1>Teste Cadastro de veiculo</h1>

	<form name="form">
		<table>
			<tr>
				<th colspan="2">Cadastrar veiculo</th>
			</tr>
			<tr>
				<td>Placa:</td>
				<td><input type="text" size="7" id="inputPlaca"></td>
			</tr>
			<tr>
				<td>Ano/Modelo:</td>
				<td><input type="text" size="4" id="inputAnoModelo"></td>
			</tr>
			<tr>
				<td>Cor:</td>
				<td><input type="text" size="7" id="inputCor"></td>
			</tr>
			<tr>
				<td>Ano/Fabricacao:</td>
				<td><input type="text" size="7" id="inputAnoFabricacao"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="button"  value="Salvar" onClick="salvar()"></td>
			</tr>
		</table>

	</form>
</body>
</html>
