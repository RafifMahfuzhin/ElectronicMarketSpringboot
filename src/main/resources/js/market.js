document.addEventListener('DOMContentLoaded', function() {
    var currentItem = 0;
  
    function showItem() {
      var item = items[currentItem];
      var nameElement = document.querySelector('.content-left .name');
      var amountElement = document.querySelector('.content-left .amount span');
  
      nameElement.textContent = item.name;
      amountElement.textContent = item.amount;
    }
  
    function navigateNext() {
      if (currentItem < items.length - 1) {
        currentItem++;
        showItem();
        document.getElementById('prevBtn').disabled = false;
      }
  
      if (currentItem === items.length - 1) {
        document.getElementById('nextBtn').disabled = true;
      }
    }
  
    function navigatePrevious() {
      if (currentItem > 0) {
        currentItem--;
        showItem();
        document.getElementById('nextBtn').disabled = false;
      }
  
      if (currentItem === 0) {
        document.getElementById('prevBtn').disabled = true;
      }
    }
  
    document.getElementById('nextBtn').addEventListener('click', navigateNext);
    document.getElementById('prevBtn').addEventListener('click', navigatePrevious);
  
    showItem();
  
    function redirectToBuyItem(itemId) {
        var buyItemUrl = '/buy-item/' + itemId; // URL buy-item yang diinginkan
        window.location.href = buyItemUrl; // Arahkan pengguna ke halaman buy-item
    }
      
  
    // Tambahkan event listener ke tombol "Buy Now"
    var buyNowButton = document.getElementById('buyNowButton');
    buyNowButton.addEventListener('click', function() {
      var selectedItem = items[currentItem]; // Ambil item yang sedang ditampilkan
      var itemId = selectedItem.id; // Ambil ID item yang dipilih
      redirectToBuyItem(itemId);
    });
  });
  