import {
  Navbar,
  Nav,
  Button,
  Col,
  NavDropdown,
  Carousel,
} from "react-bootstrap";
import "./ShopBar.css";

const ShopBar = () => {
  return (
    <Col lg={9}>
      <Navbar bg="light" variant="light" className="py-3 py-lg-0 px-0">
        <Navbar.Brand href="#">
          <h1 className="m-0 display-5 font-weight-semi-bold">
            <span className="text-primary font-weight-bold border px-3 mr-1">
              !
            </span>
            Artisan Marketplace
          </h1>
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="navbarCollapse" />
        <Navbar.Collapse
          id="navbarCollapse"
          className="justify-content-between"
        >
          <Nav className="mr-auto py-0">
            <Nav.Link href="#" className="active">
              Home
            </Nav.Link>
            <Nav.Link href="#">Shop</Nav.Link>
            <Nav.Link href="#">Shop Detail</Nav.Link>
            <NavDropdown title="Pages" id="basic-nav-dropdown">
              <NavDropdown.Item href="#">Shopping Cart</NavDropdown.Item>
              <NavDropdown.Item href="#">Checkout</NavDropdown.Item>
            </NavDropdown>
          </Nav>
        </Navbar.Collapse>
      </Navbar>

      <Carousel id="header-carousel" ride="carousel">
        <Carousel.Item className="product product-1" interval={8000}>
          <Carousel.Caption className="d-flex flex-column align-products-center justify-content-center">
            <div className="p-3" style={{ maxWidth: "700px" }}>
              <h4 className="text-light text-uppercase font-weight-medium mb-3">
                10% Off Your First Order
              </h4>
              <h3 className="display-4 text-white font-weight-semi-bold mb-4">
                Exclusive Shop Items
              </h3>
              <Button variant="light" className="py-2 px-3">
                Shop Now
              </Button>
            </div>
          </Carousel.Caption>
        </Carousel.Item>

        <Carousel.Item className="product product-2" interval={8000}>
          <Carousel.Caption className="d-flex flex-column align-products-center justify-content-center">
            <div className="p-3" style={{ maxWidth: "700px" }}>
              <h4 className="text-light text-uppercase font-weight-medium mb-3">
                10% Off Your First Order
              </h4>
              <h3 className="display-4 text-white font-weight-semi-bold mb-4">
                Hand Crafted Items
              </h3>
              <Button variant="light" className="py-2 px-3">
                Shop Now
              </Button>
            </div>
          </Carousel.Caption>
        </Carousel.Item>
      </Carousel>
    </Col>
  );
};

export default ShopBar;
