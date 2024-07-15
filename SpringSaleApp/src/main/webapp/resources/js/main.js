function deleteProduct(endpoint, productId) {
    if (confirm("Bạn chắc chắn xóa không?") === true) {
        fetch(endpoint, {
            method: "delete"
        }).then(res => {
            if (res.status === 204) {
                let d = document.getElementById(`product${productId}`);
                d.style.display = "none";
            } else
                alert("Something Wrong!");
        });
    }
}
