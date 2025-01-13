import './App.css'
import {BrowserRouter, Routes, Route} from 'react-router-dom'

import HeaderComponent from './components/HeaderComponent'
import FooterComponent from './components/FooterComponent'
import HomeComponent from './components/HomeComponent'

import ListTF_IPsComponent from './components/TF_IPsComponent'
import ListB2B_IPsComponent from './components/B2B_IPsComponent'





function App() {

  return (
    <div className="App">
      <>
      <BrowserRouter>
      <HeaderComponent/>
        <Routes>
          <Route path='/' element={<HomeComponent />} />
          <Route path='/tf_ips' element={<ListTF_IPsComponent />} />
          <Route path='/b2b_ips' element={<ListB2B_IPsComponent />} />
        </Routes>
        <FooterComponent/>
      </BrowserRouter>
      </>
    </div>
  )
}

export default App
