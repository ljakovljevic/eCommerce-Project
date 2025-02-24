import { useContext } from "react";
import { ShopContext } from "../context/ShopContext";
import { Navigate } from "react-router-dom";


function ProtectedRoute({children}){
    const { isAuthenticated } = useContext(ShopContext);

    if (!isAuthenticated) {
        return <Navigate to="/login" replace/>;
    }

    return children;
}

export default ProtectedRoute;