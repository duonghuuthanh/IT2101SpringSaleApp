import { Alert } from "bootstrap";
import { useState } from "react";
import { Button, Table } from "react-bootstrap";
import cookie from "react-cookies";

const Cart = () => {
    const [cart, setCart] = useState(cookie.load("cart") || null)
    
    if (cart === null)
        return <Alert variant="warning">KHÔNG có sản phẩm trong giỏ!</Alert>
    return (
        <>
            <h1 className="text-center text-info mt-1">GIỎ HÀNG</h1>
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Tên sản phẩm</th>
                        <th>Gía</th>
                        <th>Số lượng</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    {Object.values(cart).map(c =>  <tr key={c.id}>
                        <td>{c.id}</td>
                        <td>{c.name}</td>
                        <td>{c.price} VNĐ</td>
                        <td>{c.quantity}</td>
                        <td><Button variant="danger">&times;</Button></td>
                    </tr>)}
                   
                    
                </tbody>
                </Table>
        </>
    );
}
 
export default Cart;