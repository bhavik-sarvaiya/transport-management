// Student Transport Management System JavaScript

// Demo data for routes and schedules
const demoRoutes = [
    {
        id: 'R001',
        name: 'University Campus → Downtown',
        stops: ['Main Gate', 'Library', 'Student Center', 'Downtown Mall'],
        duration: '25 minutes',
        fare: '$2.50'
    },
    {
        id: 'R002',
        name: 'Residential Area → University',
        stops: ['Sunset Apartments', 'Park View', 'Main Campus', 'Science Building'],
        duration: '20 minutes',
        fare: '$2.00'
    },
    {
        id: 'R003',
        name: 'Airport Express',
        stops: ['University', 'City Center', 'Airport Terminal'],
        duration: '45 minutes',
        fare: '$5.00'
    }
];

const demoSchedule = [
    { route: 'R001', time: '07:30 AM', status: 'On Time' },
    { route: 'R002', time: '08:00 AM', status: 'On Time' },
    { route: 'R001', time: '08:30 AM', status: 'Delayed 5 min' },
    { route: 'R003', time: '09:00 AM', status: 'On Time' },
    { route: 'R002', time: '09:30 AM', status: 'On Time' },
    { route: 'R001', time: '10:00 AM', status: 'On Time' },
    { route: 'R003', time: '11:00 AM', status: 'On Time' },
    { route: 'R002', time: '12:00 PM', status: 'On Time' },
    { route: 'R001', time: '01:00 PM', status: 'On Time' },
    { route: 'R002', time: '02:00 PM', status: 'On Time' },
    { route: 'R003', time: '03:00 PM', status: 'On Time' },
    { route: 'R001', time: '04:00 PM', status: 'On Time' },
    { route: 'R002', time: '05:00 PM', status: 'On Time' },
    { route: 'R001', time: '06:00 PM', status: 'On Time' }
];

// Application state
let currentStudent = null;
let userBookings = [];

// DOM elements
const loginSection = document.getElementById('loginSection');
const dashboardSection = document.getElementById('dashboardSection');
const loginForm = document.getElementById('loginForm');
const welcomeName = document.getElementById('welcomeName');
const welcomeId = document.getElementById('welcomeId');
const routesList = document.getElementById('routesList');
const scheduleList = document.getElementById('scheduleList');
const routeSelect = document.getElementById('routeSelect');
const timeSelect = document.getElementById('timeSelect');
const bookingForm = document.getElementById('bookingForm');
const myBookings = document.getElementById('myBookings');
const logoutBtn = document.getElementById('logoutBtn');
const bookingDate = document.getElementById('bookingDate');

// Initialize the application
document.addEventListener('DOMContentLoaded', function() {
    // Set minimum date to today
    const today = new Date().toISOString().split('T')[0];
    bookingDate.value = today;
    bookingDate.min = today;
    
    // Populate route select options
    populateRouteSelect();
    
    // Event listeners
    loginForm.addEventListener('submit', handleLogin);
    bookingForm.addEventListener('submit', handleBooking);
    routeSelect.addEventListener('change', updateTimeOptions);
    logoutBtn.addEventListener('click', handleLogout);
    
    // Load initial data
    loadRoutes();
    loadSchedule();
});

// Handle student login
function handleLogin(event) {
    event.preventDefault();
    
    const studentId = document.getElementById('studentId').value;
    const studentName = document.getElementById('studentName').value;
    
    if (studentId && studentName) {
        currentStudent = {
            id: studentId,
            name: studentName
        };
        
        // Show dashboard and hide login
        loginSection.classList.add('hidden');
        dashboardSection.classList.remove('hidden');
        
        // Update welcome message
        welcomeName.textContent = studentName;
        welcomeId.textContent = studentId;
        
        // Load user's bookings (demo data)
        loadUserBookings();
        
        showSuccessMessage('Welcome! You have successfully logged in.');
    }
}

// Handle logout
function handleLogout() {
    currentStudent = null;
    userBookings = [];
    
    // Reset forms
    loginForm.reset();
    bookingForm.reset();
    
    // Show login and hide dashboard
    dashboardSection.classList.add('hidden');
    loginSection.classList.remove('hidden');
    
    // Clear any success messages
    const existingMessages = document.querySelectorAll('.success-message');
    existingMessages.forEach(msg => msg.remove());
}

// Load and display routes
function loadRoutes() {
    routesList.innerHTML = '';
    
    demoRoutes.forEach(route => {
        const routeElement = document.createElement('div');
        routeElement.className = 'route-item';
        routeElement.innerHTML = `
            <h4>${route.name}</h4>
            <p><strong>Route ID:</strong> ${route.id}</p>
            <p><strong>Stops:</strong> ${route.stops.join(' → ')}</p>
            <p><strong>Duration:</strong> ${route.duration} | <strong>Fare:</strong> ${route.fare}</p>
        `;
        routesList.appendChild(routeElement);
    });
}

// Load and display today's schedule
function loadSchedule() {
    scheduleList.innerHTML = '';
    
    demoSchedule.forEach(schedule => {
        const route = demoRoutes.find(r => r.id === schedule.route);
        const scheduleElement = document.createElement('div');
        scheduleElement.className = 'schedule-item';
        scheduleElement.innerHTML = `
            <h4>${schedule.time}</h4>
            <p><strong>Route:</strong> ${route ? route.name : schedule.route}</p>
            <p><strong>Status:</strong> <span class="status ${schedule.status.includes('Delayed') ? 'pending' : 'confirmed'}">${schedule.status}</span></p>
        `;
        scheduleList.appendChild(scheduleElement);
    });
}

// Populate route select dropdown
function populateRouteSelect() {
    routeSelect.innerHTML = '<option value="">Choose a route...</option>';
    
    demoRoutes.forEach(route => {
        const option = document.createElement('option');
        option.value = route.id;
        option.textContent = `${route.name} (${route.fare})`;
        routeSelect.appendChild(option);
    });
}

// Update time options based on selected route
function updateTimeOptions() {
    const selectedRouteId = routeSelect.value;
    timeSelect.innerHTML = '<option value="">Choose time...</option>';
    
    if (selectedRouteId) {
        const routeSchedules = demoSchedule.filter(s => s.route === selectedRouteId);
        
        routeSchedules.forEach(schedule => {
            const option = document.createElement('option');
            option.value = schedule.time;
            option.textContent = `${schedule.time} - ${schedule.status}`;
            timeSelect.appendChild(option);
        });
    }
}

// Handle booking submission
function handleBooking(event) {
    event.preventDefault();
    
    const routeId = routeSelect.value;
    const time = timeSelect.value;
    const date = bookingDate.value;
    
    if (routeId && time && date && currentStudent) {
        const route = demoRoutes.find(r => r.id === routeId);
        
        const booking = {
            id: `BK${Date.now()}`,
            studentId: currentStudent.id,
            routeId: routeId,
            routeName: route.name,
            time: time,
            date: date,
            fare: route.fare,
            status: 'Confirmed',
            bookingDate: new Date().toLocaleDateString()
        };
        
        userBookings.push(booking);
        loadUserBookings();
        
        // Reset form
        bookingForm.reset();
        
        // Set date back to today
        const today = new Date().toISOString().split('T')[0];
        bookingDate.value = today;
        
        showSuccessMessage(`Booking confirmed! Booking ID: ${booking.id}`);
    }
}

// Load and display user bookings
function loadUserBookings() {
    if (userBookings.length === 0) {
        myBookings.innerHTML = '<p class="no-bookings">No bookings yet. Book your first ride!</p>';
        return;
    }
    
    myBookings.innerHTML = '';
    
    userBookings.forEach(booking => {
        const bookingElement = document.createElement('div');
        bookingElement.className = 'booking-item';
        bookingElement.innerHTML = `
            <div class="booking-details">
                <h4>Booking #${booking.id}</h4>
                <p><strong>Route:</strong> ${booking.routeName}</p>
                <p><strong>Date:</strong> ${booking.date} at ${booking.time}</p>
                <p><strong>Fare:</strong> ${booking.fare}</p>
                <p><strong>Status:</strong> <span class="status confirmed">${booking.status}</span></p>
                <p><strong>Booked on:</strong> ${booking.bookingDate}</p>
            </div>
            <div class="booking-actions">
                <button class="btn btn-danger" onclick="cancelBooking('${booking.id}')">Cancel</button>
            </div>
        `;
        myBookings.appendChild(bookingElement);
    });
}

// Cancel a booking
function cancelBooking(bookingId) {
    if (confirm('Are you sure you want to cancel this booking?')) {
        userBookings = userBookings.filter(booking => booking.id !== bookingId);
        loadUserBookings();
        showSuccessMessage('Booking cancelled successfully.');
    }
}

// Show success message
function showSuccessMessage(message) {
    // Remove existing messages
    const existingMessages = document.querySelectorAll('.success-message');
    existingMessages.forEach(msg => msg.remove());
    
    // Create new message
    const messageElement = document.createElement('div');
    messageElement.className = 'success-message';
    messageElement.textContent = message;
    
    // Insert at the top of dashboard
    const welcomeCard = document.querySelector('.welcome-card');
    welcomeCard.insertAdjacentElement('afterend', messageElement);
    
    // Auto-remove message after 5 seconds
    setTimeout(() => {
        messageElement.remove();
    }, 5000);
}

// Utility function to get route name by ID
function getRouteNameById(routeId) {
    const route = demoRoutes.find(r => r.id === routeId);
    return route ? route.name : 'Unknown Route';
}