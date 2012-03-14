	.global main
	.align 4
main:
!locals=1, max_args=1
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
! [MOVE (TEMP 2) (CALL (NAME A_go) ( (VAR 1)))]
	ld [%fp-4],%l0
	st %l0,[%sp+68]
	call A_go
	nop
	mov %o0,%l2
!>> Temp t2 assigned to reg %l3
	mov %o0,%l3
! [CALLST (NAME print) ( (TEMP 2))]
	ld [%l3],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
	ret
	restore

	.align 4
A_go:
!locals=4, max_args=0
	save %sp,-112,%sp
! [MOVE (VAR 1) (CONST 1)]
	mov 1,%l0
	st %l0,[%fp-4]
! [MOVE (VAR 2) (BINOP - (CONST 1) (VAR 1))]
	mov 1,%l0
	ld [%fp-4],%l1
	sub %l0,%l1,%l0
	mov %l0,%l0
	st %l0,[%fp-8]
! [MOVE (VAR 3) (BINOP || (BINOP && (VAR 1) (VAR 2)) (VAR 1))]
	ld [%fp-4],%l0
	ld [%fp-8],%l1
	and %l0,%l1,%l0
	mov %l0,%l0
	ld [%fp-4],%l1
	or %l0,%l1,%l0
	mov %l0,%l0
	st %l0,[%fp-12]
! [CJUMP == (VAR 3) (CONST 0) (NAME L0)]
	ld [%fp-12],%l0
	mov 0,%l1
	cmp %l0,%l1
	be L0
	nop
! [MOVE (VAR 4) (CONST 1)]
	mov 1,%l0
	st %l0,[%fp-16]
! [JUMP (NAME L1)]
	ba L1
	nop
! [LABEL L0]
L0:
! [MOVE (VAR 4) (CONST 0)]
	mov 0,%l0
	st %l0,[%fp-16]
! [LABEL L1]
L1:
! [RETURN (VAR 4)]
	ret
	restore
	ret
	restore

L$1:	.asciz "%d\n"

!Total regs:  5
!Total insts: 58
