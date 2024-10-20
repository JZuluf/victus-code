document.addEventListener("DOMContentLoaded", function () {
    const homeLink = document.getElementById("home-link");
    const aboutLink = document.getElementById("about-link");
    const contactLink = document.getElementById("contact-link");
    const mainContent = document.getElementById("main-content");
    const loginForm = document.getElementById("login-form");
    const loginError = document.getElementById("login-error");

    // Función para actualizar el contenido de la página
    function updateContent(title, content) {
        mainContent.innerHTML = `<h1>${title}</h1><p>${content}</p>`;
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
            alert("Bienvenido " + email + "!");
            const modal = bootstrap.Modal.getInstance(document.getElementById('loginModal'));
            modal.hide();
            updateContent("Dashboard", "Has iniciado sesión exitosamente.");
        } else {
            loginError.innerHTML = "Credenciales incorrectas. Inténtalo de nuevo.";
        }
    });
});
