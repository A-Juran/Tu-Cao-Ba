import "../assets/css/container.scss";

import { Button, Input } from "@nextui-org/react";
import { HeartIcon, Smile, SendMsg } from "../assets/icons/icons";
import ChatList from "./container/chatItem";
// websocket
import { io } from "socket.io-client";

function initWebSocketConnect() {
  const socket = io("http://localhost:8888/");

  //connect
  socket.on("connect", (data) => {
    console.log("connect success");
    socket.emit("chat", {
      type: "MS:TEXT",
      userId: "785919644501544960",
      fromUserId: "785919644501544960", // jiangjing
      toUserId: "786600935907659776", // taohua
      content: "你好，我是江景！",
    });
  });

  //connect_error
  socket.on("connect_error", (error) => {
    console.error("WebSocket 连接失败:", error);
  });

  //dis_connect
  socket.on("disconnect", (reason) => {
    console.log("WebSocket 连接断开:", reason);
  });
}

export default function Container() {
  //连接webSocket
  initWebSocketConnect();

  return (
    <>
      <div className="container-box">
        <div className="chat-box">
          {/* 聊天内容 */}
          <div className="content">
            <ChatList />
          </div>
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
                <SendMsg className="text-2xl text-default-400 pointer-events-none flex-shrink-0" />
              </Button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
