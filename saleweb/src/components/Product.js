
import { useContext, useEffect, useState } from "react";
import { Button, Card, Col, Row, Spinner } from "react-bootstrap";
import cookie from "react-cookies";
import { useSearchParams } from "react-router-dom";
import { MyCartContext } from "../App";
import APIs, { endpoints } from "../configs/APIs";

const Product = () => {
    const [products, setProducts] = useState(null);
    const [q] = useSearchParams();
   
    const [page, setPage] = useState(1);

    const [,dispatch] = useContext(MyCartContext);

    const loadProducts = async () => {
        try {
            let url = `${endpoints['products']}?page=${page}`;

            let cId = q.get("cateId");
            if (cId !== null) {
                setPage(1);
                url = `${url}&cateId=${cId}`;
            }

            let k = q.get("kw");
            if (k !== null) {
                setPage(1);
                url = `${url}&q=${k}`;
            }

            let res = await APIs.get(url);

            console.info(res.data);

            if (page === 1)
                setProducts(res.data);
            else
                setProducts(current => [...current, ...res.data]); 

            console.info(products);
        } catch (ex) {
            console.error(ex);
        } 
    }

    useEffect(() => {
        loadProducts();
    }, [page, q]);

    const loadMore = () => {
        setPage(page + 1);
    }

    const order = (p) => {
        let cart = cookie.load("cart") || null;
        if (cart === null)
            cart = {};

        if (p.id in cart) {
            // có trong giỏ
            cart[p.id]['quantity']++;
        } else {
            // chưa có trong giỏ
            cart[p.id] = {
                "id": p.id,
                "name": p.name,
                "price": p.price,
                "quantity": 1
            };
        }

        cookie.save("cart", cart);    
        dispatch({"type": "update", "payload": 1});  
    }

    if (products === null)
        return <Spinner animation="grow" variant="primary" />;

    return (
        <>
            <Row className="mt-2">
                {products.map(p => <Col className="p-1" key={p.id} md={3} xs={12}>
                    <Card>
                        <Card.Img variant="top" src={p.image} />
                        <Card.Body>
                            <Card.Title>{p.name}</Card.Title>
                            <Card.Text>{p.price.toLocaleString("en")} VNĐ</Card.Text>
                            <Button variant="danger" className="m-1" onClick={() => order(p)}>Đặt hàng</Button>
                            <Button variant="primary" className="m-1">Xem chi tiết</Button>
                        </Card.Body>
                    </Card>
                </Col>)}
                
            </Row>

            <div className="mt-2 text-center mb-1">
                <Button onClick={loadMore} variant="primary">Xem thêm</Button>
            </div>
        </>
    );
}

export default Product;