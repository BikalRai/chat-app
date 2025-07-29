import { Link } from "react-router-dom";
import InputField from "../components/InputField";
import PrimaryGHButton from "../components/PrimaryGHButton";
import OrSeperator from "../components/OrSeperator";
import GoogleGHButton from "../components/GoogleGHButton";

const Register = () => {
  return (
    <div className='flex flex-col bg-gh-primary min-h-dvh justify-center lg:flex-row lg:items-center lg:gap-12'>
      <div className='text-gh-light grid gap-3 p-5 md:flex md:flex-col md:justify-center lg:order-2'>
        <div className='logo'>
          <img src='./images/logo.png' alt='Logo' />
        </div>
        <p className='lg:max-w-[450px]'>
          Join our community and connect with friends and teammates. Get started
          by creating your account.
        </p>
      </div>
      <div className='p-4'>
        <form className='flex flex-col gap-5 justify-center rounded-2xl bg-gh-card p-8 lg:shadow-2xl lg:rounded-4xl lg:p-12 lg:self-center lg:w-[500px] lg:order-1'>
          <h1 className='text-[32px] font-medium'>Register</h1>
          <InputField label='username' />
          <InputField type='email' label='email' />
          <InputField type='password' label='password' />
          <InputField type='password' label='confirm password' />
          <PrimaryGHButton buttonText='Register' />
          <p className='text-sm'>
            Already have an account?{" "}
            <Link
              to='/'
              className='text-gh-primary transition hover:underline hover:font-medium'
            >
              Login
            </Link>
          </p>
          <OrSeperator />
          <GoogleGHButton btnText={`Register`} />
        </form>
      </div>
    </div>
  );
};

export default Register;
