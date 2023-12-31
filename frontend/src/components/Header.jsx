import { Navbar, Nav, Container } from "react-bootstrap";
import { FaHome, FaSignInAlt, FaSignOutAlt, FaUser } from "react-icons/fa";

const Header = () => {
  return (
    <header>
      <Navbar bg="dark" variant="dark" expand="lg" collapseOnSelect>
        <Container>
          {/* <Navbar.Brand href="/">Local Artisan Marketplace</Navbar.Brand> */}
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="ms-auto">
              <Nav.Link href="/">
                <FaHome /> Home
              </Nav.Link>
              <Nav.Link href="/login">
                <FaSignInAlt /> Sign In
              </Nav.Link>
              <Nav.Link href="/login">
                <FaSignOutAlt /> Sign Up
              </Nav.Link>
              <Nav.Link href="/profile">
                <FaUser /> Profile
              </Nav.Link>
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
    </header>
  );
};

export default Header;
