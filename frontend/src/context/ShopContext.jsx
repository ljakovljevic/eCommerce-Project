import { createContext, useEffect, useMemo, useState } from "react";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import axios from '../context/AxiosInstance'

export const ShopContext = createContext();
const ShopContextProvider = (props) => {

    const currency = '$';
    const delivery_fee = 10;
    const [products, setProducts] = useState([]);
    const [search, setSearch] = useState('');
    const [showSearch, setShowSearch] = useState(false);
    const [cartItems, setCartItems] = useState([]);
    const [orders, setOrders] = useState([]);
    const [isAuthenticated, setIsAuthenticated] = useState(!!localStorage.getItem("jwtToken"));
    const navigate = useNavigate();

    const fetchAllProducts = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/products');
            setProducts(response.data);
        } catch (error) {
            console.error("Something went wrong...", error);
        }
    }
    
    

    const addToCart = async (itemId, size, quantity) => {
        if (!size) {
            toast.error('Please Select Product Size');
            return;
        }
        
        try {
            const product = {productId: itemId, size: size, quantity: quantity}
            const response = await axios.post('http://localhost:8080/user/cart', product);
            window.location.reload();
            
        
        } catch (error) {
            console.error("Something went wrong...", error);
        }
    }

    const updateQuantity = async (itemId, size, quantity) => {
        try {
            const update = {
                productId: itemId,
                size: size,
                quantity:quantity
            }
            const response = await axios.patch("http://localhost:8080/user/cart/update", update)
            window.location.reload();
            
        } catch (error) {
            console.error("Something went wrong...", error);
        }
    }

    const totalPrice = useMemo(() => {
        return cartItems.reduce((acc, item) => acc + (item.product.price * item.quantity), 0);
    }, [cartItems]);

    const cartCount = useMemo(() => {
        return cartItems.reduce((acc, item) => acc + item.quantity, 0);
    }, [cartItems]);

    const fetchOrders = async () => {
        try {
            const response = await axios.get("http://localhost:8080/user/orders");
            setOrders(response.data);
        } catch (error) {
            console.error("Something went wrong...", error);
        }
    }

    const placeOrder = async () => {
        try {
            const response = await axios.post("http://localhost:8080/user/orders");
            navigate('/orders')
        } catch (error) {
            console.error("Something went wrong...", error);
        }
    }

    const fetchCart = async () => {
        if (isAuthenticated) {
          try {
            const response = await axios.get('http://localhost:8080/user/cart');
            setCartItems(response.data);
            
          } catch (error) {
              console.error("Something went wrong...", error);
          }
        }
    }

    useEffect(() => {
        fetchCart();
    }, [isAuthenticated])
      
    useEffect(() => {
        fetchAllProducts();
    }, [])

    useEffect(() => {
        const token = localStorage.getItem("jwtToken");
        setIsAuthenticated(!!token);
    }, [])

    const value = {
        products, currency, delivery_fee,
        search, setSearch, showSearch, setShowSearch,
        cartItems, setCartItems, addToCart, fetchCart, cartCount,
        updateQuantity, totalPrice,
        navigate, 
        isAuthenticated, setIsAuthenticated,
        orders, fetchOrders, placeOrder
    }

    return (
        <ShopContext.Provider value={value}>
            {props.children}
        </ShopContext.Provider>
    )
}

export default ShopContextProvider;