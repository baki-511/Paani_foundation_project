document.addEventListener('DOMContentLoaded', function() {
    // Animation for activity rows when they come into view
    const animateOnScroll = function() {
        const activityRows = document.querySelectorAll('.activity-row');

        activityRows.forEach(row => {
            const rowPosition = row.getBoundingClientRect().top;
            const windowHeight = window.innerHeight;

            if (rowPosition < windowHeight - 100) {
                row.style.opacity = '1';
                row.style.transform = 'translateY(0)';
            }
        });
    };

    // Set initial state for animation
    const activityRows = document.querySelectorAll('.activity-row');
    activityRows.forEach(row => {
        row.style.opacity = '0';
        row.style.transform = 'translateY(30px)';
        row.style.transition = 'opacity 0.6s ease, transform 0.6s ease';
    });

    // Run on load and scroll
    animateOnScroll();
    window.addEventListener('scroll', animateOnScroll);

    // Add hover effect for touch devices
    function setupTouchHover() {
        if ('ontouchstart' in window) {
            document.querySelectorAll('.activity-row').forEach(row => {
                row.addEventListener('touchstart', function() {
                    this.classList.add('hover-effect');
                });

                row.addEventListener('touchend', function() {
                    setTimeout(() => {
                        this.classList.remove('hover-effect');
                    }, 200);
                });
            });
        }
    }

    setupTouchHover();
});
