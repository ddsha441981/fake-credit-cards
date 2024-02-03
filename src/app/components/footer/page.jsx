const Footer = () =>{
    return(
        <>
            <div className="container-fluid">
                <footer className="bg-dark text-white text-center p-3">
                    <div className="container">
                        <p className="mb-0">by Deendayal Kumawat &copy; {new Date().getFullYear()}</p>
                    </div>
                </footer>
            </div>

        </>
    );
};
export default Footer;