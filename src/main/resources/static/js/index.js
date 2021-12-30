const datos = new Vue({
    data: {
        usuario: {}
    },
    created() {
        this.cargarUsuario()
    },
    methods: {
        async cargarUsuario() {

            let url = `http://localhost:8080/api/usuario`

            let opciones = {
                method: 'GET',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    'Authorization': await localStorage.token
                }
            }

            const request = await fetch(url, opciones);
            this.usuario = await request.json();

            console.log(this.usuario);
        },
        async eliminarUsuario() {

            if (confirm('¿Desea eliminar este usuario?')) {
                
                let url = `http://localhost:8080/api/usuario`

                let opciones = {
                    method: 'DELETE',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json',
                        'Authorization': await localStorage.token
                    }
                }

                const request = await fetch(url, opciones);

                window.location.href = './pages/registrar.html'
            }
        }
    }
})

new Vue({
    el: 'main'
})
