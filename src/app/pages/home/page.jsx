import Navbar from "@/app/components/navbar/page";
import Footer from "@/app/components/footer/page";
import Header from "@/app/components/header/page";
import Card from "@/app/pages/card/page";

const Home = () =>{
    return(
        <>
            <div className="container-fluid">
                <Navbar></Navbar>
                <Header></Header>
                <Card></Card>
                <Footer></Footer>
            </div>
        </>
    );
};
export default Home;