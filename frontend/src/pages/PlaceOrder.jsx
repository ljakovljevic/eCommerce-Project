import React, { useContext, useState } from 'react'
import Title from '../components/Title'
import CartTotal from '../components/CartTotal'
import { assets } from '../assets/frontend_assets/assets'
import { ShopContext } from '../context/ShopContext'

const PlaceOrder = () => {

  const { placeOrder } = useContext(ShopContext);  
  const [method, setMethod] = useState('cod');
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: '',
    country: '',
    city: '',
    postalCode: '',
    street: '',
    phone: ''
  })

  const handlePlaceOrder = () => {
    const { firstName, lastName, email, country, city, postalCode, street, phone } = formData;

    if (
      !firstName.trim() || 
      !lastName.trim() ||
      !email.trim() ||
      !country.trim() ||
      !city.trim() ||
      !postalCode.trim() ||
      !street.trim() ||
      !phone.trim()
    ) {
      alert("Please fill in all delivery informations fields!");
      return
    }
    placeOrder();
  }

  return (
    <div className='flex flex-col sm:flex-row justify-between gap-4 pt-5 sm:pt-14 mih-h-[80vh] border-t'>
      {/* Left Side */}
      <div className='flex flex-col gap-4 w-full sm:max-w-[480px]'>

        <div className='text-xl sm:text-2xl my-3'>
          <Title text1={'DELIVERY'} text2={'INFORMATIONS'}/>
        </div> 
        <div className='flex gap-3 '>
          <input onChange={(e) => setFormData({...formData, firstName: e.target.value})} className='border border-gray-300 rounded py-1.5 px-3.5 w-full' type="text" placeholder='First Name' />
          <input onChange={(e) => setFormData({...formData, lastName: e.target.value})} className='border border-gray-300 rounded py-1.5 px-3.5 w-full' type="text" placeholder='Last Name' />
        </div>
        <input onChange={(e) => setFormData({...formData, email: e.target.value})} className='border border-gray-300 rounded py-1.5 px-3.5 w-full' type="email" placeholder='Email Address' />
        <input onChange={(e) => setFormData({...formData, country: e.target.value})} className='border border-gray-300 rounded py-1.5 px-3.5 w-full' type="text" placeholder='Country' />
        <div className='flex gap-3 '>
          <input onChange={(e) => setFormData({...formData, city: e.target.value})} className='border border-gray-300 rounded py-1.5 px-3.5 w-full' type="text" placeholder='City' />
          <input onChange={(e) => setFormData({...formData, postalCode: e.target.value})} className='border border-gray-300 rounded py-1.5 px-3.5 w-full' type="number" placeholder='Postal Code' />
        </div>
        <input onChange={(e) => setFormData({...formData, street: e.target.value})} className='border border-gray-300 rounded py-1.5 px-3.5 w-full' type="text" placeholder='Street' />
        <input onChange={(e) => setFormData({...formData, phone: e.target.value})} className='border border-gray-300 rounded py-1.5 px-3.5 w-full' type="number" placeholder='Phone' />

      </div>

      {/* Right Side */}
      <div className='mt-8'>

        <div className='mt-8 min-w-80'>
          <CartTotal />
        </div>

        <div className='mt-12'>
          <Title text1={'PAYMENT'} text2={'METHOD'} /> 
          <div className='flex gap-3 flex-col lg:flex-row'>
            <div onClick={() => setMethod('stripe')} className='flex items-center gap-3 border p-2 px-3 cursor-pointer'> 
              <p className={`min-w-3.5 h-3.5 border rounded-full ${method === 'stripe' ? 'bg-green-400' : ''}`}></p>
              <img src={assets.stripe_logo} className='h-5 mx-4' alt="" />
            </div>
            <div onClick={() => setMethod('razorpay')} className='flex items-center gap-3 border p-2 px-3 cursor-pointer'> 
              <p className={`min-w-3.5 h-3.5 border rounded-full ${method === 'razorpay' ? 'bg-green-400' : ''}`}></p>
              <img src={assets.razorpay_logo} className='h-5 mx-4' alt="" />
            </div>
            <div onClick={() => setMethod('cod')} className='flex items-center gap-3 border p-2 px-3 cursor-pointer'> 
              <p className={`min-w-3.5 h-3.5 border rounded-full ${method === 'cod' ? 'bg-green-400' : ''}`}></p>
              <p className='text-gray-500 text-sm font-medium mx-4'>CASH ON DELIVERY</p>
            </div>
          </div>
        </div>

        <div className='w-full text-end mt-8'>
          <button onClick={() => handlePlaceOrder()} className='bg-black text-white px-16 py-3 text-sm'>PLACE ORDER</button>
        </div>

      </div>
    </div>
  )
}

export default PlaceOrder
