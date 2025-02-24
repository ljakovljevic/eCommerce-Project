import React, { useContext, useEffect, useState } from 'react'
import axios from '../context/AxiosInstance'
import { ShopContext } from '../context/ShopContext';

const Login = () => {

  const { navigate, setIsAuthenticated, fetchCart } = useContext(ShopContext);
  const [currentState, setCurrentState] = useState('Login');
  const [formData, setFormData] = useState({username: '', email: '', password: ''})

  const {email, ...loginData} = formData;

  const onSubmitHandler = async (event) => {
    event.preventDefault();

    if (currentState === 'Login') {
      try {
        const response = await axios.post('http://localhost:8080/user/login', loginData)

        localStorage.setItem("jwtToken", response.data);
        setIsAuthenticated(true);

        navigate("/")
        window.location.reload();
  
      } catch (error) {
        console.error("Login wasn't successful, something went wrong..", error);
      }
    }
    else{
      try {
        const response = await axios.post('http://localhost:8080/user/register', formData)
        console.log(response.data);
        setCurrentState('Login');
      } catch (error) {
        console.error("Registration wasn't succesful, something went wrong...", error);
      }
    }
  }

  return (
    <form onSubmit={onSubmitHandler} className='flex flex-col items-center w-[90%] sm:max-w-96 m-auto mt-14 gap-4 text-gray-800'>
      <div className='inline-flex items-center gap-2 mb-2 mt-10'>
        <p className='prata-regular text-3xl'>{currentState}</p>
        <hr className='border-none h-[1.5px] w-8 bg-gray-800'/>
      </div>
      <input onChange={(e) => setFormData({...formData, username: e.target.value})} type="text" className='w-full px-3 py-2 border border-gray-800' placeholder='Username' required />
      <input onChange={(e) => setFormData({...formData, email: e.target.value})} type="email" className={`w-full px-3 py-2 border border-gray-800 ${currentState === 'Login' ? 'hidden' : '' }`} placeholder='Email Address' required={currentState !== 'Login'} />
      <input onChange={(e) => setFormData({...formData, password: e.target.value})} type="password" className='w-full px-3 py-2 border border-gray-800' placeholder='Password' required />
      <div className='w-full flex justify-between text-sm mt-[-8px]'>
        <p className='cursor-pointer'>Forgot your password?</p>
        {
          currentState === 'Login' ?
          <p onClick={() => setCurrentState('Sign up')} className='cursor-pointer'>Don't have account?</p> :
          <p onClick={() => setCurrentState('Login')} className='cursor-pointer'>Already have account?</p>
        }
      </div>
      <button className='bg-black text-white font-light px-8 py-2 mt-4'>{currentState === 'Login' ? 'Sign In' : 'Sign Up'}</button>
    </form>
  )
}

export default Login

