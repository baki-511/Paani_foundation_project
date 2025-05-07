document.addEventListener('DOMContentLoaded', function() {
    // Toggle password visibility
    const togglePassword = document.getElementById('togglePassword');
    const password = document.getElementById('password');

    togglePassword.addEventListener('click', function() {
        const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
        password.setAttribute('type', type);
        this.classList.toggle('fa-eye-slash');
    });

    // Form submission
    const signinForm = document.querySelector('.signin-form');

    signinForm.addEventListener('submit', function(e) {
        e.preventDefault();

        // Here you would typically validate and submit the form
        // For demo purposes, we'll just log the values
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;
        const remember = document.getElementById('remember').checked;

        console.log('Email:', email);
        console.log('Password:', password);
        console.log('Remember me:', remember);

        // Simulate successful login
        alert('Login successful! Redirecting...');
        // window.location.href = 'dashboard.html'; // Redirect to dashboard
    });

    // Add animation to form elements
    const formGroups = document.querySelectorAll('.form-group');

    formGroups.forEach((group, index) => {
        group.style.opacity = '0';
        group.style.transform = 'translateY(20px)';
        group.style.transition = `opacity 0.5s ease ${index * 0.1}s, transform 0.5s ease ${index * 0.1}s`;

        setTimeout(() => {
            group.style.opacity = '1';
            group.style.transform = 'translateY(0)';
        }, 100);
    });
});
