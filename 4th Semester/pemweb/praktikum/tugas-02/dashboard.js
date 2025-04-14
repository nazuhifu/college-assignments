document.addEventListener('DOMContentLoaded', function() {
    const userInfo = document.querySelector('.user-info');
    const dropdownMenu = document.querySelector('.dropdown-menu');
    
    // Dropdown menu
    userInfo.addEventListener('click', function(e) {
        e.stopPropagation();
        dropdownMenu.style.display = dropdownMenu.style.display === 'block' ? 'none' : 'block';
    });
    
    // Close dropdown
    document.addEventListener('click', function() {
        dropdownMenu.style.display = 'none';
    });
    
    // Sidebar nav active state
    const navItems = document.querySelectorAll('.sidebar nav ul li');
    navItems.forEach(item => {
        item.addEventListener('click', function() {
            navItems.forEach(i => i.classList.remove('active'));
            this.classList.add('active');
        });
    });
    
    simulasiDataLoading();
});

function simulasiDataLoading() {
    const statValues = document.querySelectorAll('.stat-value');
    
    // Hide values initially
    statValues.forEach(stat => {
        stat.style.opacity = '0';
    });
    
    // Delay
    setTimeout(() => {
        statValues.forEach((stat, index) => {
            setTimeout(() => {
                stat.style.transition = 'opacity 0.5s ease';
                stat.style.opacity = '1';
            }, index * 200);
        });
    }, 500);
}