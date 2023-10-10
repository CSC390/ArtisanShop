import {
  Navbar,
  Nav,
  Button,
  Col,
  NavDropdown,
  Carousel,
} from "react-bootstrap";
import { FaCartPlus, FaFirstdraft } from "react-icons/fa";
import "./ShopBar.css";

const ShopBar = () => {
  return (
    <Col lg={9} className="pt-3">
      <Navbar bg="light" variant="light" className="py-3 py-lg-0 px-0 mb-3">
        <Navbar.Brand href="#">
          <h1 className="m-0 display-4 font-weight-semi-bold">
            <span className="text-primary font-weight-bold border px-2 m-1">
              <FaFirstdraft className="text-beaver" />
            </span>
            Artisan Marketplace
          </h1>
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="navbarCollapse" />
        <Navbar.Collapse
          id="navbarCollapse"
          className="justify-content-between"
        >
          <Nav className="ms-auto">
            <Nav.Link href="/cart">
              <FaCartPlus />
              Shopping Cart
            </Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Navbar>

      <Carousel id="header-carousel" ride="carousel">
        <Carousel.Item className="item item-1" interval={8000}>
          <Carousel.Caption className="d-flex flex-column align-items-center justify-content-center">
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

        <Carousel.Item className="item item-2" interval={8000}>
          <Carousel.Caption className="d-flex flex-column align-items-center justify-content-center">
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
