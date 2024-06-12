import axios from "axios";
import { Container, Nav, Navbar } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { useUserStore } from "../store/userStore";

const NavBar = () => {
  const navigate = useNavigate();
  const clearUser = useUserStore((state) => state.clearUser);
  const { user } = useUserStore((state) => state);

  console.log("user in navbar", user);

  const logOut = () => {
    const accountData = {
      token: localStorage.getItem("token"),
    };

    axios
      .post("http://localhost:8080/api/auth/logout", accountData)
      .then((response) => {
        console.log(response);
        localStorage.removeItem("token");
        localStorage.removeItem("authToken");
        clearUser();
        navigate("/");
      })
      .catch((error) => console.log(error));
  };

  return (
    <Navbar bg="dark" data-bs-theme="dark">
      <Container>
        <Navbar.Brand onClick={logOut}>book</Navbar.Brand>
        <Nav className="me-auto">
          <Nav.Link href="/">home</Nav.Link>
          <Nav.Link href="books">books</Nav.Link>
          <Nav.Link href="book_categories">categories</Nav.Link>
          <Nav.Link href="users">users</Nav.Link>
          <Nav.Link href="logout">logout</Nav.Link>
        </Nav>
      </Container>
    </Navbar>
  );
};

export default NavBar;
