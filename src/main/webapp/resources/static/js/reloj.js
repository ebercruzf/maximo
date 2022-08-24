function cargaReloj () {
	document.getElementById('reloj').innerHTML = new Date().toLocaleString('es-MX', {
		timeZone: "America/Mexico_City",
		weekday: 'long',
		day: 'numeric',
		month: 'long',
		year: 'numeric',
		hour: 'numeric',
		minute: 'numeric',
		second: 'numeric',
		hour12: true
	});
}