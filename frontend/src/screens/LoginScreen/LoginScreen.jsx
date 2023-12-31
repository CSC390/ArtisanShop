import { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Container, Form, Button, Row, Col } from "react-bootstrap";
import { useDispatch, useSelector } from "react-redux";
import FormContainer from "../../components/FormContainer";
import "./LoginScreen.css";
import { useLoginMutation } from "../../reducers/usersApiSlice";
import { setCredentials } from "../../reducers/authReducer";
import { toast } from "react-toastify";
import Loader from "../../components/Loader";

const LoginScreen = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate();
  const dispatch = useDispatch();

  const [login, { isLoading }] = useLoginMutation();

  const { userInfo } = useSelector((state) => state.auth);

  useEffect(() => {
    if (userInfo) {
      navigate("/");
    }
  }, [navigate, userInfo]);

  const SubmitHandler = async (e) => {
    e.preventDefault();
    try {
      const res = await login({ email, password }).unwrap();
      dispatch(setCredentials({ ...res }));
      navigate("/");
    } catch (err) {
      toast.error(err?.data?.message || err.error);
    }
  };

  return (
    <div className="full-height-container">
      <Container className="h-100">
        <Row className="justify-content-center align-products-center h-100">
          <Col xs={12} sm={10} md={8} lg={12}>
            <FormContainer>
              <h1 className="text-center">Sign In</h1>
              <Form onSubmit={SubmitHandler}>
                <Form.Group className="my-2" controlId="email">
                  <Form.Label>Email Address</Form.Label>
                  <Form.Control
                    type="email"
                    placeholder="Enter Email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    style={{ width: "100%" }}
                  />
                </Form.Group>

                <Form.Group className="my-2" controlId="password">
                  <Form.Label>Password</Form.Label>
                  <Form.Control
                    type="password"
                    placeholder="Enter password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    style={{ width: "100%" }}
                  />
                </Form.Group>

                {isLoading && <Loader />}

                <Button type="submit" variant="success" className="fs-16 mt-5">
                  Sign In
                </Button>
                <Row className="pt-5">
                  <Col className="pb-4">New Customer?</Col>
                  <a
                    className="fs-16 btn btn-secondary"
                    href="/register"
                    role="button"
                  >
                    Register
                  </a>
                </Row>
              </Form>
            </FormContainer>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default LoginScreen;

<Row className="pt-5">
  <Col className="pb-4">New Customer?</Col>
  <Button type="submit" variant="secondary" className="fs-16">
    Login
  </Button>
</Row>;
