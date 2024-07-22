import 'bootstrap/dist/css/bootstrap.min.css';
import { useEffect, useState } from 'react';
import { Button, Col, Container, Form, Nav, Navbar, NavDropdown, Row } from 'react-bootstrap';
import Product from './components/Product';
import APIs, { endpoints } from './configs/APIs';

const App = () => {
  const [categories, setCategories] = useState([]);

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

  return (
    <>
      <Navbar expand="lg" className="bg-body-tertiary">
      <Container>
        <Navbar.Brand href="#home">E-commerce Website</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link href="#home">Trang chủ</Nav.Link>
   
            <NavDropdown title="Danh mục" id="basic-nav-dropdown">
              {categories.map(c => <NavDropdown.Item key={c.id} href="#action/3.1">{c.name}</NavDropdown.Item>)}
            </NavDropdown>
          </Nav>
        </Navbar.Collapse>

        <Form inline>
          <Row>
            <Col xs="auto">
              <Form.Control
                type="text"
                placeholder="Tìm sản phẩm..."
                className=" mr-sm-2"
              />
            </Col>
            <Col xs="auto">
              <Button type="submit">Tìm</Button>
            </Col>
          </Row>
        </Form>
      </Container>
    </Navbar>

    <Container>
      <Product />
    </Container>
    </>
  );
}

export default App