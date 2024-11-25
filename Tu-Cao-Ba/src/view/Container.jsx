import "../assets/css/container.scss";

import { Button, Input } from "@nextui-org/react";
import { HeartIcon, Smile, SendMsg } from "../assets/icons/icons";

export default function Container() {
  return (
    <>
      <div className="container">
        <div className="chat-box">
          {/* 聊天内容 */}
          <div className="content"></div>

          {/* 聊天信息发送框 */}
          <div className="send-msg">
            <div className="send-msg-box">
              {/* 按钮 */}
              <Button isIconOnly color="white" aria-label="Like">
                <Smile className="text-2xl text-default-400 pointer-events-none flex-shrink-0" />
              </Button>
              {/* 输入框 */}
              <Input type="email" />
              {/* 发送按钮 */}
              <Button isIconOnly color="white" aria-label="Like">
                <SendMsg className="text-2xl text-default-400 pointer-events-none flex-shrink-0"/>
              </Button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
