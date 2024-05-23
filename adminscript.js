document.addEventListener('DOMContentLoaded', () => {
    const newPostBtn = document.getElementById('newpbtn');
    const newForumBtn = document.getElementById('newfbtn');
    const modal = document.getElementById('modal');
    const closeModalBtn = document.querySelector('.closebtn');
    const modalTitle = document.getElementById('modaltitle');
    const inputTitle = document.getElementById('inputtitle');
    const inputContent = document.getElementById('inputcontent');
    const submitBtn = document.getElementById('submitbtn');
    const forumsContainer = document.getElementById('forums');
    const newAnnounceBtn = document.getElementById('announcebtn');
    const repbtn = document.getElementById('repbtn')
    let isCreatingPost = false;
//divide the middle section better and make forums open new page with entire forum
//add reply btns and add view more replies which opens to entire post

    newPostBtn.addEventListener('click', () => {
        modalTitle.textContent = 'Create New Post';
        inputTitle.value = '';
        inputContent.value = '';
        modal.style.display = 'flex';
        isCreatingPost = true;
    });

    newForumBtn.addEventListener('click', () => {
        modalTitle.textContent = 'Create New Forum';
        inputTitle.value = '';
        inputContent.value = '';
        modal.style.display = 'flex';
        isCreatingPost = false;
    });

    closeModalBtn.addEventListener('click', () => {
        modal.style.display = 'none';
    });

    window.addEventListener('click', (event) => {
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    });

    submitBtn.addEventListener('click', () => {
        const title = inputTitle.value.trim();
        const content = inputContent.value.trim();

        if (title && content) {
            const newElement = document.createElement('div');
            newElement.className = isCreatingPost ? 'post' : 'forum';
            newElement.innerHTML = `
                <div class="post-content">
                    <h3 class="posts">${title}</h3>
                    <p class="postcont">${content}</p>
                    <div class="button-container">
                        <button class="reply">View replies</button>
                        <button class="reply">Reply</button>
                        <button class="admindelete">Delete</button>
                    </div>
                </div>`;
            forumsContainer.appendChild(newElement);
            modal.style.display = 'none';
        }
         else {
            alert('Title and content cannot be empty.');
        }
    });

    newAnnounceBtn.addEventListener('click', () => {
        modalTitle.textContent = 'Create Announcement';
        inputTitle.value = '';
        inputContent.value = '';
        modal.style.display = 'flex';
        isCreatingPost = true;
    });
});
