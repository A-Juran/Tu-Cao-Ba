//Css
import "../assets/css/layout.scss";
//Layout
import Sidebar from "../view/Sidebar";
import Header from "../view/Header";
import Container from "../view/Container";
import Footer from "../view/Footer";

export default function Index() {
  return (
    <div className="Layout-container">
      {/* page Box */}
      <div className="Layout-box">
        {/* Sidebar */}
        <div className="Layout-left">
          <Sidebar />
        </div>
        {/* Header|Container */}
        <div className="Layout-right">
          <Header />
          <Container />
        </div>
      </div>
      {/* Footer */}
      <div className="Layout-footer">
        <Footer />
      </div>
    </div>
  );
}
