import { useContext, useState } from "react";
import { Button, Form } from "react-bootstrap";
import cookie from "react-cookies";
import { Navigate } from "react-router";
import { MyDispatchContext, MyUserContext } from "../App";
import APIs, { authAPIs, endpoints } from "../configs/APIs";

const Login = () => {
    const [username, setUsername] = useState();
    const [password, setPassword] = useState();
    const user = useContext(MyUserContext);
    const dispatch = useContext(MyDispatchContext);

    const login = async (e) => {
        e.preventDefault();

       try {
            let res = await APIs.post(endpoints['login'], {
                "username": username, 
                "password": password
            });

        
            cookie.save("access-token", res.data)

            let user = await authAPIs().get(endpoints['current-user']);
            cookie.save("user", user.data);

            dispatch({
                "type": "login",
                "payload": user.data
            });
       } catch(ex) {
           console.error(ex);
       }
    }

    if (user !== null)
        return <Navigate to="/" />

    return (<>
        <h1 className="text-center text-info mt-1">ĐĂNG NHẬP NGƯỜI DÙNG</h1>

        <Form method="post" onSubmit={login}>
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                <Form.Label>Tên đăng nhập</Form.Label>
                <Form.Control type="text" placeholder="Tên đăng nhập..." value={username} onChange={e => setUsername(e.target.value)} />
            </Form.Group>
            <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                <Form.Label>Mật khẩu</Form.Label>
                <Form.Control type="password" placeholder="Mật khẩu..." value={password} onChange={e => setPassword(e.target.value)}  />
            </Form.Group>
            <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                <Button type="submit" variant="success">Đăng nhập</Button>
            </Form.Group>
        </Form>
    </>);
}

export default Login;