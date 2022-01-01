const datos = new Vue({
    methods: {
        async iniciarSesion() {
            let datos = {};
            datos.numero = document.getElementById('txtNumero').value;
            datos.pin = document.getElementById('txtPin').value;

            let url = `http://localhost:8080/api/login`

            let opciones = {
                method: 'POST',
                body: JSON.stringify(datos),
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            }

            const request = await fetch(url, opciones);
            const respuesta = await request.text();

            if (respuesta != 'FAIL' && request.status === 200 && request.ok) {
                localStorage.token = respuesta;
                window.location.href = './pagina-de-pruebas.html'
            } else {
                alert("Las credenciales son incorrectas. Por favor intente nuevamente.");
            }
        }
    }
})

new Vue({
    el: 'main'
})
