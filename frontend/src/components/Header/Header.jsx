import { Navbar, Nav, Container, NavDropdown } from "react-bootstrap";
import { useSelector, useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { LinkContainer } from "react-router-bootstrap";
import {
  FaHome,
  FaSignInAlt,
  FaSignOutAlt,
  FaStore,
  FaUser,
} from "react-icons/fa";
import { useLogoutMutation } from "../../reducers/usersApiSlice";
import { clearCredentials } from "../../reducers/authReducer";
import "./Header.css";

const Header = () => {
  const { userInfo } = useSelector((state) => state.auth);

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const [logoutApiCall] = useLogoutMutation();

  const logoutHandler = async () => {
    try {
      await logoutApiCall().unwrap();
      dispatch(clearCredentials());
      navigate("/login");
    } catch (err) {
      console.log(err);
    }
  };
  return (
    <header>
      <Navbar bg="dark" variant="dark" expand="lg" collapseOnSelect>
        <Container>
          {/* <Navbar.Brand href="/">Local Artisan Marketplace</Navbar.Brand> */}
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="ms-auto">
              <LinkContainer to="/">
                <Nav.Link>
                  <FaHome /> Home
                </Nav.Link>
              </LinkContainer>
              {userInfo ? (
                <>
                  <LinkContainer to="/myshop">
                    <Nav.Link>
                      <FaStore /> My Shop
                    </Nav.Link>
                  </LinkContainer>

                  <LinkContainer to="/profile">
                    <Nav.Link>
                      <FaUser /> Profile
                    </Nav.Link>
                  </LinkContainer>

                  <NavDropdown title={userInfo.name} id="username">
                    <NavDropdown.Item onClick={logoutHandler}>
                      Logout
                    </NavDropdown.Item>
                  </NavDropdown>
                </>
              ) : (
                <>
                  <LinkContainer to="/login">
                    <Nav.Link>
                      <FaSignInAlt /> Sign In
                    </Nav.Link>
                  </LinkContainer>

                  <LinkContainer to="/register">
                    <Nav.Link>
                      <FaSignOutAlt /> Sign Up
                    </Nav.Link>
                  </LinkContainer>
                </>
              )}
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
    </header>
  );
};

export default Header;
