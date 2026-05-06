function ReviewImage(){
var image=["assets/reviewimage.webp","assets/reviewimage2.webp","assets/reviewimage3.webp"];
var index=0;
var slider=document.getElementById("slider");
slider.addEventListener("click",function(){
    index=(index+1)%image.length;
    slider.src=image[index];
});
}
function CountTime(){
    let endDate = new Date("2026-12-31T23:59:59").getTime();
    let countEl = document.getElementById("counttime");
    if (!countEl) return;

    let x = setInterval(function(){
        let now = new Date().getTime();
        let distance = endDate - now;

        if (distance < 0) {
            clearInterval(x);
            countEl.innerHTML = "<h2>Khuyến Mãi</h2>";
            return;
        }

        let days    = Math.floor(distance / (1000 * 60 * 60 * 24));
        let hours   = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        let minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        let seconds = Math.floor((distance % (1000 * 60)) / 1000);
        countEl.innerHTML = "Khuyến mãi còn " +
            days + " Ngày " + hours + " Giờ " + minutes + " Phút " + seconds + " Giây";
    }, 1000);
}
function ShowDesc(){
    // Hàm này chỉ dùng trên trang product_detail, không cần chạy ở index
    var DescImg = document.getElementById("desc_img_0");
    if (!DescImg) return;
    [1,2,3,4].forEach(function(i){
        var thumb = document.getElementById("desc_img_" + i);
        if (thumb) thumb.addEventListener("click", function(){ DescImg.src = thumb.src; });
    });
}
function init(){
    ReviewImage();
    CountTime();
    ShowDesc();
}
window.onload=init;
