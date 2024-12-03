import "../assets/css/header.scss";
import OnloineUser from "./header/Online";
import {Chip} from "@nextui-org/react";
import { CheckIcon } from "../assets/icons/icons";

export default function Header() {
  return (
    <>
      <div className="header text-center">
        {/* 在线用户 */}
        <div className="online-user">
          <OnloineUser />
        </div>
        {/* 聊天室|用户名 */}
        <div className="title">JR'Bike</div>
        {/* 在线状态 */}
        <div className="online-status">
          <Chip
            startContent={<CheckIcon size={18} />}
            variant="faded"
            color="success"
          >
            Ok
          </Chip>
        </div>
      </div>
    </>
  );
}
