document.addEventListener('DOMContentLoaded', function() {
    // Mobile Navigation Toggle
    const hamburger = document.querySelector('.hamburger');
    const navLinks = document.querySelector('.nav-links');

    hamburger.addEventListener('click', () => {
        hamburger.classList.toggle('active');
        navLinks.classList.toggle('active');
    });

    // Close mobile menu when clicking on a link
    document.querySelectorAll('.nav-links ul li a').forEach(link => {
        link.addEventListener('click', () => {
            hamburger.classList.remove('active');
            navLinks.classList.remove('active');
        });
    });

    // Navbar scroll effect
    window.addEventListener('scroll', () => {
        const navbar = document.querySelector('.navbar');
        if (window.scrollY > 50) {
            navbar.classList.add('scrolled');
        } else {
            navbar.classList.remove('scrolled');
        }
    });

    // Amount Selection
    const amountOptions = document.querySelectorAll('.amount-option');
    const amountInput = document.getElementById('amount');

    amountOptions.forEach(option => {
        option.addEventListener('click', function() {
            // Remove active class from all options
            amountOptions.forEach(opt => opt.classList.remove('active'));

            // Add active class to clicked option
            this.classList.add('active');

            // Set the input value
            amountInput.value = this.getAttribute('data-amount');
        });
    });

    // Custom amount handling
    amountInput.addEventListener('input', function() {
        // Remove active class from all options when typing custom amount
        amountOptions.forEach(opt => opt.classList.remove('active'));
    });

    // FAQ Accordion
    const faqQuestions = document.querySelectorAll('.faq-question');

    faqQuestions.forEach(question => {
        question.addEventListener('click', function() {
            this.classList.toggle('active');
            const answer = this.nextElementSibling;

            if (this.classList.contains('active')) {
                answer.style.maxHeight = answer.scrollHeight + 'px';
            } else {
                answer.style.maxHeight = '0';
            }
        });
    });

    // Form Submission


    // Close Modal
    const modalClose = document.querySelector('.modal-close');

    modalClose.addEventListener('click', function() {
        donationModal.classList.remove('active');
    });

    // Close modal when clicking outside
    donationModal.addEventListener('click', function(e) {
        if (e.target === donationModal) {
            donationModal.classList.remove('active');
        }
    });

    // Share button
    const shareBtn = document.querySelector('.share-btn');

    shareBtn.addEventListener('click', function(e) {
        e.preventDefault();
        // In a real app, this would open share dialog
        alert('Share functionality would open here!');
    });
});

// Add these to your existing donate.js

// Payment Method Selection
const paymentMethods = document.querySelectorAll('input[name="payment"]');
const upiDetails = document.getElementById('upi-details');
const cardDetails = document.getElementById('card-details');
const netbankingDetails = document.getElementById('netbanking-details');

paymentMethods.forEach(method => {
    method.addEventListener('change', function() {
        // Hide all payment details first
        upiDetails.style.display = 'none';
        cardDetails.style.display = 'none';
        netbankingDetails.style.display = 'none';

        // Show selected payment method
        if (this.value === 'upi') {
            upiDetails.style.display = 'block';
        } else if (this.value === 'card') {
            cardDetails.style.display = 'block';
        } else if (this.value === 'netbanking') {
            netbankingDetails.style.display = 'block';
        }
    });
});

// UPI Option Selection
const upiOptions = document.querySelectorAll('.upi-option');
const upiQr = document.querySelector('.upi-qr');
const upiId = document.querySelector('.upi-id');

upiOptions.forEach(option => {
    option.addEventListener('click', function() {
        // Remove active class from all options
        upiOptions.forEach(opt => opt.classList.remove('active'));

        // Add active class to clicked option
        this.classList.add('active');

        // Show relevant content
        if (this.dataset.type === 'qr') {
            upiQr.style.display = 'block';
            upiId.style.display = 'none';
        } else {
            upiQr.style.display = 'none';
            upiId.style.display = 'block';
        }
    });
});

// Card Number Formatting
const cardNumber = document.getElementById('card-number');
if (cardNumber) {
    cardNumber.addEventListener('input', function() {
        let value = this.value.replace(/\s+/g, '').replace(/[^0-9]/gi, '');
        let matches = value.match(/\d{4,16}/g);
        let match = matches && matches[0] || '';
        let parts = [];

        for (let i=0, len=match.length; i<len; i+=4) {
            parts.push(match.substring(i, i+4));
        }

        if (parts.length) {
            this.value = parts.join(' ');
        }
    });
}

// Expiry Date Formatting
const cardExpiry = document.getElementById('card-expiry');
if (cardExpiry) {
    cardExpiry.addEventListener('input', function() {
        let value = this.value.replace(/\s+/g, '').replace(/[^0-9]/gi, '');
        if (value.length > 2) {
            this.value = value.substring(0, 2) + '/' + value.substring(2, 4);
        }
    });
}

// CVV Formatting
const cardCvv = document.getElementById('card-cvv');
if (cardCvv) {
    cardCvv.addEventListener('input', function() {
        this.value = this.value.replace(/\s+/g, '').replace(/[^0-9]/gi, '');
    });
}

// Verify UPI ID
const verifyBtn = document.querySelector('.verify-btn');
if (verifyBtn) {
    verifyBtn.addEventListener('click', function() {
        const upiId = document.getElementById('upi-id').value;
        if (!upiId || !upiId.includes('@')) {
            alert('Please enter a valid UPI ID (e.g. 96222422@upi)');
            return;
        }

        // In a real app, you would verify the UPI ID here
        alert(`Payment request sent to ${upiId}. Please approve the payment in your UPI app.`);
    });
}
