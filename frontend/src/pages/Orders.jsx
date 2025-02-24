import React, { useContext, useEffect } from 'react'
import { ShopContext } from '../context/ShopContext'
import Title from '../components/Title';

const Orders = () => {

  const { orders, fetchOrders, currency } = useContext(ShopContext);

  useEffect(() => {
    fetchOrders();
  },[])

  return (
    <div className='border-t pt-16'>
      <div className='text-2xl'>
        <Title text1={'MY'} text2={'ORDERS'}/>
      </div>

      <div>
        {
          orders.map((item,index) => (
            <div key={index} className='py-4 border-t border-b text-gray-700 flex flex-col md:flex-row md:items-center md:justify-between gap-4'>
              <div className='flex items-start gap-6 text-sm'>
                <img src={item.product.image} className='w-16 sm:w-20' alt="" />
                <div>
                  <p className='sm:text-base font-medium'>{item.product.name}</p>
                  <div className='flex items-center gap-3 mt-2 text-base text-gray-700'>
                    <p className='text-lg'>{currency}{item.product.price}</p>
                    <p>Quantity: {item.quantity}</p>
                    <p>Size: {item.size}</p>
                  </div>
                  <p className='mt-2'>Date: <span className='text-gray-400'>{item.orderedAt}</span></p>
                </div>
              </div>
              <div className='md:w-1/2 flex justify-between'>
                <div className='flex items-center gap-2'>
                  <p className='min-w-2 h-2 rounded-full bg-green-500'></p>
                  <p className='text-sm md:text-base'>Ready To Ship</p>
                </div>
                <button className='border px-4 py-2 text-sm font-medium rounded-sm hover:bg-gray-300'>Track Your Order</button>
              </div>
            </div>
          ))
        }
      </div>
    </div>
  )
}

export default Orders
