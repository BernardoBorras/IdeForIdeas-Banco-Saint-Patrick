const datos = new Vue({
    data: {
        usuario: {}
    },
    created() {
        if (localStorage.token) {
            this.cargarUsuario()
        }
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
        cerrarSesion() {
            localStorage.removeItem("token");
            location.reload()
        },
    }
})

new Vue({
    el: 'header'
})