
<%@page import="model.Provincias"%>
<%@page import="dao.ProvinciaDAO"%>
<%@page import="model.Localidades"%>
<%@page import="java.util.List"%>
<%@page import="dao.LocalidadDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>SAINT PATRICK</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->

  <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
  <link href="assets/vendor/aos/aos.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
  <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="assets/css/style.css" rel="stylesheet">

</head>
<body>

  <!-- ======= Header ======= -->
  <header id="header" class="fixed-top d-flex align-items-center">
    <div class="container d-flex align-items-center">
      <h1 class="logo me-auto"><a href="index.html">SAINT PATRICK<span></span></a></h1>

      <a href="index.html" class="logo me-auto"><img src="assets/img/favicon.png" alt="Logo"></a>

      <nav id="navbar" class="navbar order-last order-lg-0">
        <ul>
          <li><a class="nav-link scrollto active" href="#hero">Transacción</a></li>
          <li><a class="nav-link scrollto" href="#about">Saldo</a></li>
          <li><a class="nav-link scrollto" href="#team">Historial</a></li>
        </ul>
        <i class="bi bi-list mobile-nav-toggle"></i>
      </nav><!-- .navbar -->

      <a href="index.html" class="get-started-btn scrollto">Salir</a>
    </div>
  </header><!-- End Header -->

<div class="wrapper col-md-12 col-lg-12 col-xl-12" data-aos="fade-down-left" data-aos-delay="100">
	<div class="container">
		<div class="py-4">

			<h3>Transferencia</h3>
		</div>

<div class="col-md-12">
	<form action="../controller/guardarPedido.jsp">
				<hr class="mb-6">
 			<h4 class="mb-3">Formas de Pago</h4>

				<label class="custom-control custom-radio">
				  <input type="radio" name="paymentMethod" id="paymentMethod" value="Tarjeta de credito" class="custom-control-input">
				  <span class="custom-control-indicator"></span>
				  <span class="custom-control-description">Tarjeta de credito</span>
				</label>
				<br>
				<label class="custom-control custom-radio">
					<input type="radio" name="paymentMethod" id="paymentMethod" value="Mercado Pago" class="custom-control-input">
					<span class="custom-control-indicator"></span>
					<span class="custom-control-description">Mercado Pago</span>
				</label>


				<div class="row">
					<div class="col-md-6 mb-3">
						<label for="titular">Nombre de usuario receptor (Opcional)</label>
						<input name="cc-name" id="cc-name" class="form-control" required placeholder="Ejemplo: Daniel Videla">
						<small class="text-muted">Ingrese el nombre como aparece en la tarjeta</small>
					</div>
					<div class="col-md-6 mb-3">
						<label for="nroTarjeta">Numero de Tarjeta receptor (Obligatorio)</label>
						<input type="number" name="number" id="number" class="form-control" required placeholder="xxxx - xxxx - xxxx - xxxx">>
						<small class="text-muted">Ingrese los 16 digitos de la tarjeta</small>
					</div>
				</div>

				<div class="row">
					<div class="col-md-3 mb-3"></div>
					<div class="col-md-3 mb-3">
						<label for="fechaVto">Monto a enviar</label>

            <input  type="number" name="expiration" id="expiration" class="form-control" required placeholder="$">
						<small class="text-muted">Ingrese monto, solo numeros</small>
					</div>
					<div class="col-md-3 mb-3">
						<label for="codigoSeg">Codigo de Seguridad - Pin</label>
						<input type="password" name="cvv" id="cvv" class="form-control" required>
						<small class="text-muted">Ingrese los 4 dgitos</small>
					</div>
				</div>

        <div class=" center">
					<button type="submit" class="get-started-btn scrollto">Enviar</button>
				</div>

			</form>


		</div>
	</div>





	<!-- Bootstrap JavaScript Libraries -->
    <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
		integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
		crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
		integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
		crossorigin="anonymous"></script>
</body>

</html>
<script>
  AOS.init();
</script>
