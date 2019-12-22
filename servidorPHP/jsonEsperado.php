<?php

/*  Formato JSON esperado */

$arrEsperado = array();
$arrZapatillaEsperado = array();

$arrEsperado["peticion"] = "add";

$arrZapatillaEsperado["nombre"] = "Nike (Un string)";

$arrEsperado["zapatillaAnnadir"] = $arrZapatillaEsperado;


/* Funcion para comprobar si el recibido es igual al esperado */

function JSONCorrectoAnnadir($recibido){
	
	$auxCorrecto = false;
	
	if(isset($recibido["peticion"]) && $recibido["peticion"] ="add" && isset($recibido["zapatillaAnnadir"])){
		
		$auxZapatilla = $recibido["zapatillaAnnadir"];
		if(isset($auxZapatilla["nombre"])){
			$auxCorrecto = true;
		}
		
	}
	
	
	return $auxCorrecto;
	
}
