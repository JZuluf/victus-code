document.addEventListener("DOMContentLoaded", function () {
    const homeLink = document.getElementById("home-link");
    const aboutLink = document.getElementById("about-link");
    const contactLink = document.getElementById("contact-link");
    const mainContent = document.getElementById("main-content");
    const loginForm = document.getElementById("login-form");
    const loginError = document.getElementById("login-error");
    const userInfo = document.getElementById("user-info");
    let loggedInUser = null;

    // Función para actualizar el contenido de la página
    function updateContent(title, content) {
        mainContent.innerHTML = `<h1>${title}</h1><p>${content}</p>`;
    }

    // Función para mostrar la galería de zonas comunes
    function showGallery() {
        mainContent.innerHTML = `
            <h1>Zonas Comunes Disponibles</h1>
            <div class="gallery">
                <div class="gallery-item">
                    <img src="images/Designer.jpeg" alt="Zona Designer">
                    <h3>Zona Designer</h3>
                    <button class="btn btn-primary">Reservar</button>
                </div>
                <div class="gallery-item">
                    <img src="images/fondo.jpg" alt="Fondo Residencial">
                    <h3>Fondo Residencial</h3>
                    <button class="btn btn-primary">Reservar</button>
                </div>
                <div class="gallery-item">
                    <img src="images/vts.jpg" alt="VTS Residencial">
                    <h3>VTS Residencial</h3>
                    <button class="btn btn-primary">Reservar</button>
                </div>
            </div>
        `;
    }

    // Navegación básica
    homeLink.addEventListener("click", function () {
        updateContent("Bienvenido a Victus Residencias", "Esta es la página principal. Use el menú para navegar entre las secciones.");
    });

    aboutLink.addEventListener("click", function () {
        updateContent("Acerca de Nosotros", "Esta página habla sobre Victus Residencias y nuestros servicios.");
    });

    contactLink.addEventListener("click", function () {
        updateContent("Contáctenos", "Puede contactarnos a través de correo electrónico en info@victus.com.");
    });

    // Funcionalidad de login
    loginForm.addEventListener("submit", function (event) {
        event.preventDefault();
        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;

        // Simple autenticación mock
        if (email === "juanpa@admin.com" && password === "password") {
            loginError.innerHTML = "";
            loggedInUser = { name: "Juan Pablo", email: email };
            userInfo.innerHTML = `Usuario: ${loggedInUser.name} (${loggedInUser.email})`;
            document.getElementById("login-btn").style.display = "none";

            alert("Bienvenido " + email + "!");
            const modal = bootstrap.Modal.getInstance(document.getElementById('loginModal'));
            modal.hide();
            showGallery(); // Mostrar galería tras iniciar sesión
        } else {
            loginError.innerHTML = "Credenciales incorrectas. Inténtalo de nuevo.";
        }
    });
});
