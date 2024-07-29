import { useContext, useEffect, useState } from 'react';
import { Badge, Button, Col, Container, Form, Image, Nav, Navbar, NavDropdown, Row } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom';
import { MyCartContext, MyDispatchContext, MyUserContext } from '../App';
import APIs, { endpoints } from '../configs/APIs';

const Header = () => {
    const [categories, setCategories] = useState([]);
    const user = useContext(MyUserContext)
    const dispatch = useContext(MyDispatchContext);
    const [kw, setKw] = useState("");
    const nav = useNavigate();
    const [cartCounter, ] = useContext(MyCartContext);

    const loadCates = async () => {
        try {
          let res = await APIs.get(endpoints['categories']);
          setCategories(res.data);
        } catch (ex) {
          console.error(ex);
        }
      }

    useEffect(() => {
        loadCates();
      }, []);

    const submit = (e) => {
        e.preventDefault();

        nav(`/?kw=${kw}`);
    }

    return (
        <>
        <Navbar expand="lg" className="bg-body-tertiary">
            <Container>
                <Navbar.Brand href="#home">E-commerce Website</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                <Nav className="me-auto">
                    <Link className='nav-link' to="/">Trang chủ</Link>
        
                    <NavDropdown title="Danh mục" id="basic-nav-dropdown">
                    {categories.map(c => {
                        const url = `/?cateId=${c.id}`;
                        return <Link to={url} className='dropdown-item' key={c.id}>{c.name}</Link>;
                    })}
                    </NavDropdown>

                    {user===null?<>
                        <Link className='nav-link text-success' to="/login">&#129489; Đăng nhập</Link>
                        <Link className='nav-link text-success' to="/register">&#129489; Đăng ký</Link>
                        
                        
                    </>:<>
                         <Link className='nav-link text-success' to="/login">
                         <Image src={user.avatar} width="25" roundedCircle />
                              Chào {user.username}!</Link>
                    <Button variant='danger' onClick={() => dispatch({"type": "logout"})}>Đăng xuất</Button>
                    </>}
                    <Link className='nav-link text-danger' to="/cart">&#128722; <Badge bg='danger'>{cartCounter}</Badge></Link>
                </Nav>
                </Navbar.Collapse>

                <Form inline onSubmit={submit}>
                    <Row>
                        <Col xs="auto">
                        <Form.Control
                            type="text"
                            placeholder="Tìm sản phẩm..."
                            className=" mr-sm-2"
                            value={kw}
                            onChange={e => setKw(e.target.value)}
                        />
                        </Col>
                        <Col xs="auto">
                        <Button type="submit">Tìm</Button>
                        </Col>
                    </Row>
                </Form>
            </Container>
            </Navbar>
        </>
    );
}

export default Header;