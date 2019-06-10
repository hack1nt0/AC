
map <C-c> "+y
map <C-v> "+p

set exrc
set mouse=a

set noeb vb t_vb=

set smarttab
set autoindent
set cindent

set showcmd
set number
set autowrite
set autoread
set nowrap

syntax on

set clipboard=unnamedplus

nnoremap <C-W> :bdelete<CR>
nnoremap <C-N> :bnext<CR>
nnoremap <C-P> :bprev<CR>
nnoremap <F5> :!g++ -fmax-errors=1 -Wshadow -Wcast-qual -Wcast-align -Wno-unused-result -std=c++17 -O2 -DLOCAL -o %< % <CR>
noremap <F6> :!./%< < in <CR>
noremap <F7> :!./%< <CR>
noremap <SPACE> :!<CR>

