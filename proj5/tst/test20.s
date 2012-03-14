	.align 4
A_go:
!locals=1, max_args=2
	save %sp,-96,%sp
! [MOVE (VAR 1) (CONST 0)]
	mov 0,%l0
	st %l0,[%fp-4]
! [CJUMP <= (PARAM 1) (CONST 0) (NAME L0)]
	ld [%fp+72],%l0
	mov 0,%l1
	cmp %l0,%l1
	ble L0
	nop
! [CALLST (NAME print) ( (PARAM 1))]
	ld [%fp+72],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
! [MOVE (TEMP 2) (CALL (NAME A_back) ( (PARAM 0) (BINOP - (PARAM 1) (CONST 1))))]
	ld [%fp+68],%l0
	st %l0,[%sp+68]
	ld [%fp+72],%l2
	mov 1,%l3
	sub %l2,%l3,%l2
	ld [%l2],%l1
	st %l1,[%sp+68]
	call A_back
	nop
	mov %o0,%l2
!>> Temp t2 assigned to reg %l3
	mov %o0,%l3
! [MOVE (VAR 1) (TEMP 2)]
	mov %l3,%l2
	st %l2,[%fp-4]
! [LABEL L0]
L0:
! [RETURN (VAR 1)]
	ret
	restore
	ret
	restore

	.align 4
A_back:
!locals=1, max_args=2
	save %sp,-96,%sp
! [MOVE (TEMP 3) (CALL (NAME A_go) ( (PARAM 0) (PARAM 1)))]
	ld [%fp+68],%l0
	st %l0,[%sp+68]
	ld [%fp+72],%l1
	st %l1,[%sp+68]
	call A_go
	nop
	mov %o0,%l2
!>> Temp t3 assigned to reg %l3
	mov %o0,%l3
! [MOVE (VAR 1) (TEMP 3)]
	mov %l3,%l2
	st %l2,[%fp-4]
! [RETURN (CONST 0)]
	ret
	restore
	ret
	restore

	.global main
	.align 4
main:
!locals=1, max_args=2
	save %sp,-96,%sp
! [MOVE (TEMP 1) (CALL (NAME malloc) ( (NAME wSZ)))]
	mov 4,%o0
	call malloc
	nop
	mov %o0,%l0
!>> Temp t1 assigned to reg %l1
	mov %o0,%l1
! [MOVE (VAR 1) (TEMP 1)]
	mov %l1,%l0
	st %l0,[%fp-4]
! [MOVE (TEMP 4) (CALL (NAME A_go) ( (VAR 1) (CONST 5)))]
	ld [%fp-4],%l0
	st %l0,[%sp+68]
	ld [5],%l2
	st %l2,[%sp+68]
	call A_go
	nop
	mov %o0,%l3
!>> Temp t4 assigned to reg %l4
	mov %o0,%l4
! [CALLST (NAME print) ( (TEMP 4))]
	ld [%l4],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
	ret
	restore

L$1:	.asciz "%d\n"

!Total regs:  6
!Total insts: 76
