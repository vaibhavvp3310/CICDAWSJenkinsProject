// Get references to HTML elements
const registerForm = document.getElementById("registerForm");
const loginForm = document.getElementById("loginForm");
const registerMessage = document.getElementById("registerMessage");
const loginMessage = document.getElementById("loginMessage");
const welcomeContainer = document.getElementById("welcome-container");
const welcomeMessage = document.getElementById("welcomeMessage");
const registerContainer = document.getElementById("register-container");
const loginContainer = document.getElementById("login-container");

let user = {};

// test comment
// Handle user registration
registerForm.addEventListener("submit", function (event) {
  event.preventDefault();

  const username = document.getElementById("registerUsername").value;
  const password = document.getElementById("registerPassword").value;

  if (username && password) {
    // Save user credentials
    user = { username, password };
    registerMessage.textContent = "Registration successful! Please log in.";
    registerMessage.style.color = "green";
    console.log("User registered:", user);

    // Clear form fields
    document.getElementById("registerUsername").value = "";
    document.getElementById("registerPassword").value = "";

    // Switch to login form after registration
    registerContainer.classList.add("hidden"); // Hide register form
    loginContainer.classList.remove("hidden"); // Show login form
    console.log("Switched to login form");
  } else {
    registerMessage.textContent = "Please fill out both fields.";
    registerMessage.style.color = "red";
  }
});

// Handle user login
loginForm.addEventListener("submit", function (event) {
  event.preventDefault();

  const username = document.getElementById("loginUsername").value;
  const password = document.getElementById("loginPassword").value;

  if (username === user.username && password === user.password) {
    // Display welcome message
    welcomeMessage.textContent = `Hello, ${username}`;
    welcomeContainer.classList.remove("hidden");

    // Hide login form
    loginContainer.classList.add("hidden");
    console.log("User logged in successfully:", username);
  } else {
    loginMessage.textContent = "Invalid username or password.";
    loginMessage.style.color = "red";
    console.log("Login failed");
  }
});
