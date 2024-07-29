import 'bootstrap/dist/css/bootstrap.min.css';
import { createContext, useReducer } from 'react';

import { Container } from 'react-bootstrap';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Cart from './components/Cart';
import Login from './components/Login';
import Product from './components/Product';
import Register from './components/Register';

import Footer from './layout/Footer';
import Header from './layout/Header';
import MyCartReducer from './reducers/MyCartReducer';
import MyUserReducer from './reducers/MyUserReducer';

export const MyUserContext = createContext();
export const MyDispatchContext = createContext();
export const MyCartContext = createContext();

const App = () => {
  const [user, dispatch] = useReducer(MyUserReducer, null);
  const [cartCounter, cartDispatch] = useReducer(MyCartReducer, 0);

  return (
    <MyUserContext.Provider value={user}>
      <MyDispatchContext.Provider value={dispatch}>
        <MyCartContext.Provider value={[cartCounter, cartDispatch]}>
          <BrowserRouter>
            <Header />
            <Container>
              <Routes>
                <Route path='/' element={<Product />} />
                <Route path='/login' element={<Login />} />
                <Route path='/register' element={<Register />} />
                <Route path='/cart' element={<Cart />} />
              </Routes>
            </Container>
            <Footer />
          </BrowserRouter>
        </MyCartContext.Provider>
      </MyDispatchContext.Provider>
    </MyUserContext.Provider>
    
  );
}

export default App