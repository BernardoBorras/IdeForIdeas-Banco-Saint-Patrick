const datos = new Vue({
    methods: {
        async registrarUsuario() {

            let datosIngresados = {};

            let txtNombre = document.getElementById('txtNombre').value;
            datosIngresados.nombre = this.limpiarNombreApellido(txtNombre)

            let txtApellido = document.getElementById('txtApellido').value
            datosIngresados.apellido = this.limpiarNombreApellido(txtApellido)

            let txtEmail = document.getElementById('txtEmail').value
            datosIngresados.email = this.limpiarEmail(txtEmail)

            datosIngresados.contrasenia = document.getElementById('txtContrasenia').value;
            let repetirContrasenia = document.getElementById('txtRepetirContrasenia').value;

            console.log('Contraseña valida: ' + this.contraseniaValida(datosIngresados.contrasenia, repetirContrasenia))
            console.log('Email valido: ' + this.emailValido(datosIngresados.email))
            console.log('Nombre/apellido valido: ' + this.nombresValidos([datosIngresados.nombre, datosIngresados.apellido]))
            console.log(datosIngresados)

            if (this.contraseniaValida(datosIngresados.contrasenia, repetirContrasenia) &&
                this.emailValido(datosIngresados.email) &&
                this.nombresValidos([datosIngresados.nombre, datosIngresados.apellido])) {

                let url = `http://localhost:8080/api/usuario`

                let opciones = {
                    method: 'POST',
                    body: JSON.stringify(datosIngresados),
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    }
                }
                const request = await fetch(url, opciones);
                if(request.ok && request.status === 200){
                    window.location.href = './login.html'
                }
                else{
                    alert("Ups! Algo salió mal");
                }
            }
        },
        limpiarNombreApellido(cad) {
            cad = cad.trim()
            cad = cad.split(' ')
            cad = cad.filter(cad => cad.length > 1)
            cad = cad.map(cad => cad.toUpperCase().substring(0, 1) + cad.toLowerCase().substring(1))
            cad = cad.reduce((a, b) => a + ' ' + b)
            return cad
        },
        limpiarEmail(email){
            email = email.trim()
            email = email.toLowerCase()
            return email
        },
        contraseniaValida(contrasenia, repetirContrasenia) {
            return (contrasenia === repetirContrasenia &&
                contrasenia.match(/[a-z]/g) &&
                contrasenia.match(/[A-Z]/g) &&
                contrasenia.match(/[0-9]/g) &&
                contrasenia.length >= 8 &&
                contrasenia.length <= 16)
        },
        nombresValidos(nombres) {
            return (nombres.every(nombre => !nombre.match(/[0-9]/g)) &&
                nombres.every(nombre => nombre.length >= 2) &&
                nombres.every(nombre => nombre.length <= 36))
        },
        emailValido(email) {
            return (!email.includes(' ') &&
                email.includes('@') &&
                email.includes('.') &&
                email.match(/[a-z]/g) &&
                email.length <= 64)
        },
    }
})

new Vue({
    el: 'main'
})
