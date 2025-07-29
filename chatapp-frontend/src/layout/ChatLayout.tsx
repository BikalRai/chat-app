import Nav from "../components/Nav";

const ChatLayout = () => {
  return (
    <div>
      <aside className='min-h-dvh w-24 bg-gh-light py-10 grid justify-items-center overflow-clip'>
        <div className='logo'>
          <img src='./images/logo2.png' alt='Logo' />
        </div>
        <Nav />
      </aside>
    </div>
  );
};

export default ChatLayout;
