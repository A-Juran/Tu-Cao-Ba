import { SuccessIcon, Menu } from "../../assets/icons/icons";

// 聊天记录

export default function ChatList() {
  return (
    <div className="chat-list">
      <ChatItem />
    </div>
  );
}

function ChatItem() {
  const chatList = [
    {
      id: 1,
      isMine: false,
      content: "i don't remember anything",
      sendTime: "18:12",
      sendStauts: true,
    },
    {
      id: 2,
      isMine: true,
      content:
        " OMG © do you remember what you did last nightat the work night out?",
      sendTime: "18:12",
      sendStauts: true,
    },
    {
      id: 3,
      isMine: false,
      content: "i don't remember anything",
      sendTime: "18:12",
      sendStauts: true,
    },
    {
      id: 4,

      isMine: true,
      content:
        " OMG © do you remember what you did last nightat the work night out?",
      sendTime: "18:12",
      sendStauts: true,
    },
    {
      id: 5,

      isMine: false,
      content: "i don't remember anything",
      sendTime: "18:12",
      sendStauts: true,
    },
    {
      id: 6,

      isMine: true,
      content:
        " OMG © do you remember what you did last nightat the work night out?",
      sendTime: "18:12",
      sendStauts: true,
    },
    {
      id: 7,

      isMine: false,
      content: "i don't remember anything",
      sendTime: "18:12",
      sendStauts: true,
    },
    {
      id: 8,

      isMine: true,
      content:
        " OMG © do you remember what you did last nightat the work night out?",
      sendTime: "18:12",
      sendStauts: true,
    },
    {
      id: 9,

      isMine: false,
      content: "i don't remember anything",
      sendTime: "18:12",
      sendStauts: true,
    },
    {
      id: 10,

      isMine: true,
      content:
        " OMG © do you remember what you did last nightat the work night out?",
      sendTime: "18:12",
      sendStauts: true,
    },
    {
      id: 11,
      isMine: false,
      content: "i don't remember anything",
      sendTime: "18:12",
      sendStauts: true,
    },
    {
      id: 12,
      isMine: true,
      content:
        " OMG © do you remember what you did last nightat the work night out?",
      sendTime: "18:12",
      sendStauts: true,
    },
  ];

  const items = chatList.map((item) => (
    <div
      className={
        item.isMine
          ? "chat-item chat-list-position-right"
          : "chat-item chat-list-position-left"
      }
      key={item.id}
    >
      <div className={item.isMine ? "chat-item-is-mine" : "chat-item-not-mine"}>
        {/* 发送的内容 */}
        <div className="chat-content">{item.content}</div>
        {/* 发送的提示|时间 */}
        <div className="chat-tips">
          <div className="chat-time">{item.sendTime}</div>
          <div className="chat-status">
            <SuccessIcon fill="#000" />
          </div>
        </div>
      </div>
    </div>
  ));

  console.log(items);

  return items;
}
